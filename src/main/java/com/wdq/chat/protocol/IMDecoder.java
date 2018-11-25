package com.wdq.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wudq
 * @Date: 2018/11/2
 */
public class IMDecoder extends ByteToMessageDecoder {

    //解析IM写一下请求内容的正则
    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String msg = in.toString(Charset.defaultCharset());
        System.out.println("-----接收到消息:"+msg);
    }

    /**
     * 消息解码
     * 不同类型的消息指令，解码成不同的消息体
     */
    public IMMessage decode(String msg) {
        System.out.println("MSG:"+msg);
        Matcher matcher = pattern.matcher(msg);
        String header = null;
        String content = "";

        if(matcher.matches()) {
            header = matcher.group(1);
            content = matcher.group(3);
        }

        String [] headers = header.split("\\]\\[");
        long time = 0;
        try{ time = Long.parseLong(headers[1]); } catch(Exception e){}
        String nickName = headers[2];
        for (String heard : headers) {
            System.out.println("heard:"+heard);
        }

        if(msg.startsWith("[" + IMProtocol.LOGIN.getName() + "]")){
            return new IMMessage(headers[0],time,nickName);
        }else if(msg.startsWith("[" + IMProtocol.CHAT.getName() + "]")){
            return new IMMessage(headers[0],time,nickName,content);
        }else if(msg.startsWith("[" + IMProtocol.FLOWER.getName() + "]")){
            return new IMMessage(headers[0],time,nickName);
        }else{
            return null;
        }
    }
}