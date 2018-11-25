package com.wdq.chat.server;

import com.wdq.chat.initialzer.ChatServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * 聊天服务器
 * @Author: wudq
 * @Date: 2018/10/21
 */
//@Component
public class ChatServer implements ApplicationContextAware {
    private int port = 10086;

//    public ChatServer(int port) {
//        this.port = port;
//    }

    @PostConstruct
    public void start() throws Exception{
        //用于服务器端接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //进行SocketChannel网络通信 、数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建指定类型和属性的Channel
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChatServerChannelInitializer()) //初始化
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 长连接
            // 绑定端口，开始接收进来的连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync(); // (7)
            System.err.println("---服务器成功启动，端口："+port);
            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            channelFuture.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

//    public static void main(String[] args) {
//        try {
//            new ChatServer(10086).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}