package com.wdq.chat.handler;

import com.wdq.chat.processor.IMProcessor;
import com.wdq.chat.protocol.IMDecoder;
import com.wdq.chat.protocol.IMProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocket) throws Exception {
        String context = textWebSocket.text();
        //不是IM协议
        if(!IMProtocol.isMP(context)) {
        }
        IMProcessor processor = new IMProcessor();
        processor.sendMsg(ctx.channel(), context);
//        ctx.writeAndFlush(new TextWebSocketFrame("我收到消息了老哥"));
    }

    /**
     * 将连接进来的客户端加入连接队列
    */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

}