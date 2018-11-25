package com.wdq.chat.client;

import com.wdq.chat.initialzer.ChatClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class ChatClient {

    public void start() throws  Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class) // (3)
                     .handler(new ChatClientChannelInitializer())
                     .option(ChannelOption.SO_KEEPALIVE, true);  //长连接

            ChannelFuture channelFuture = bootstrap.connect("localhost",8080).sync();
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine();
                if("quit".equals(input)) {
                    System.exit(1);
                    break;
                }
                channelFuture.channel().writeAndFlush(input);
            }
            channelFuture.channel().closeFuture().sync();
    } finally {
            System.exit(1);
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        try {
            new ChatClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}