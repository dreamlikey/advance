package com.wdq.chat.initialzer;

import com.wdq.chat.handler.ChatClientHandler;
import com.wdq.chat.test.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author: wudq
 * @Date: 2018/10/29
 */
public class ChatClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ChatClientHandler());
        ch.pipeline().addLast(new StringEncoder()); //管道中添加字符编码
    }
}