package com.wdq.chat.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        if (byteBuf.isReadable()) {
            ctx.writeAndFlush("---已连接上服务器！");
            System.out.println(byteBuf.toString(Charset.defaultCharset()));
        }
    }

}