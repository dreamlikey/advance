package com.wdq.chat.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

/**
 * Http协议handler
 * @Author: wudq
 * @Date: 2018/11/1
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public URL baseUrl = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();
    public final String webroot = "webroot";

    /**
     * @Author wudq
     * 根据路径获取资源
    */
    public File getResource(String uri) throws Exception {
        String page = uri.equals("/") ? "/chat.html" : uri;
        String path = baseUrl.toURI() + webroot +page;
        System.out.println("----:"+path);
        path = !path.contains("file:") ? path : path.substring(5);
        path = path.replaceAll("//", "/");
        File file = new File(path);
        return file;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println("---------:"+ ctx.name());
        String uri = request.getUri();
        RandomAccessFile accessFile = null;
        try {
            //文件流
            accessFile = new RandomAccessFile(getResource(uri),"r");
        } catch (Exception e) {
            ctx.fireChannelRead(request.retain());
            return;
        }

//        DefaultFullHttpResponse response = new DefaultFullHttpResponse(
//                request.getProtocolVersion(), HttpResponseStatus.OK);
        //http响应
        HttpResponse response = new DefaultHttpResponse(
                request.getProtocolVersion(), HttpResponseStatus.OK);

        //Content-type
        String contentType = "text/html;";
        if(uri.endsWith(".css")) {
            contentType = "text/css;";
        } else if(uri.endsWith(".js")) {
            contentType = "application/javascript;";
        } else if(uri.toLowerCase().matches("(jps|png|gif|jpeg)$")) {
            String ext = uri.substring(uri.lastIndexOf("."));
            contentType = "image/" + ext;
        }
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE,contentType+"charset=utf-8;");
        boolean isKeepAlive = HttpHeaders.isKeepAlive(request);
        if(isKeepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, accessFile.length());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        //响应
        ctx.write(response);

        // 为当前文件流创建唯一的文件通道（FileChannel），通过这个文件通道将文件流写入一个文件区域(FileRegion)
        //通过netty的线程通道，传输响应
        ctx.write(new DefaultFileRegion(accessFile.getChannel(), 0, accessFile.length()));
//        ctx.write(new ChunkedNioFile(file.getChannel()));

        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!isKeepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
        accessFile.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel client = ctx.channel();
        System.err.println("Client:"+client.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}