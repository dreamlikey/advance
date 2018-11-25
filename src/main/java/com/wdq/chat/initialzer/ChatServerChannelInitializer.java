package com.wdq.chat.initialzer;

import com.wdq.chat.handler.HttpHandler;
import com.wdq.chat.handler.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.catalina.Pipeline;

/**
 * @Author: wudq
 * @Date: 2018/10/29
 */
public class ChatServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        /** 解析Http请求 */
        pipeline.addLast(new HttpServerCodec());
        //主要是将同一个http请求或响应的多个消息对象变成一个 fullHttpRequest完整的消息对象
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //主要用于处理大数据流,比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的 ,加上这个handler我们就不用考虑这个问题了
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpHandler());

        //================WebSocket=====================
        pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
        pipeline.addLast(new WebSocketHandler());
    }
}