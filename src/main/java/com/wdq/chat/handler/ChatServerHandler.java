package com.wdq.chat.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    //客户端队列
    static List<Channel> channels = new ArrayList<Channel>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String context = byteBuf.toString(Charset.defaultCharset());
        //ctx.write()与channel().write()的区别
        //ctx.write()调用的是倒数第一个outbound的write()方法,如果没有outbound，数据无法write发送数据
        //channel.write()管道最后一个Handler
        for (Channel channel : channels) {
            ctx.channel().remoteAddress();
            channel.writeAndFlush(ctx.channel().remoteAddress() + ":" + context);
//            ctx.writeAndFlush(ctx.channel().remoteAddress() + ":" + context);
        }
    }

    /**
     * 将连接进来的客户端加入连接队列
    */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        if (!channels.isEmpty()) {
            if(channels.contains(channel)) {
                System.out.println("客户端【"+address.toString()+"】已经在聊天室中");
            }
            for (Channel inChannel : channels) {
                inChannel.writeAndFlush("客户端【"+address+"】加入聊天室");
            }
        }
        channels.add(channel);
        System.out.println("客户端【"+address.toString()+"】加入聊天室");
    }

    /**
     * 客户端断开连接，重连接队列中移除
    */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        if(channels == null) {
//            return;
//        }
//        Channel channel = ctx.channel();
//        if(channels.contains(channel)) {
//            channels.remove(channel);
//            System.out.println("客户端【"+channel.remoteAddress().toString()+"】退出聊天室");
//        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }



}