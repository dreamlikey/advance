package com.wdq.chat.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class NettyClient {

    public void run() throws  Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class) // (3)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        public void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    })
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
            new NettyClient().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}