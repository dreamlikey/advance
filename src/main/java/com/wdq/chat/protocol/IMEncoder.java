package com.wdq.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: wudq
 * @Date: 2018/11/2
 */
public class IMEncoder extends MessageToByteEncoder<IMMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IMMessage msg, ByteBuf out) throws Exception {

    }

    /**
     * 消息编码
     * 不同类型的消息指令，编码成不同的消息体
     */
    public String encode(IMMessage msg) {
        if(null == msg){ return ""; }
        String prex = "[" + msg.getCmd() + "]" + "[" + msg.getTime() + "]";
        if(IMProtocol.LOGIN.getName().equals(msg.getCmd()) ||
                IMProtocol.CHAT.getName().equals(msg.getCmd()) ||
                IMProtocol.FLOWER.getName().equals(msg.getCmd())){
            prex += ("[" + msg.getSender() + "]");
        }else if(IMProtocol.SYSTEM.getName().equals(msg.getCmd())){
            prex += ("[" + msg.getOnline() + "]");
        }
        if(!(null == msg.getContent() || "".equals(msg.getContent()))){
            prex += (" - " + msg.getContent());
        }
        return prex;
    }
}