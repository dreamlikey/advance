package com.wdq.chat.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author: wudq
 * @Date: 2018/10/28
 */
public class NettyServer {
    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        //用于服务器端接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //进行SocketChannel网络通信 、数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() { // (4)
                        @Override
                        public void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                            ch.pipeline().addLast(new StringEncoder()); //管道中添加字符编码
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 长连接

            // 绑定端口，开始接收进来的连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync(); // (7)

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            channelFuture.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        try {
            new NettyServer(8080).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}