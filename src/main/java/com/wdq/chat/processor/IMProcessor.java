package com.wdq.chat.processor;

import com.wdq.chat.protocol.IMDecoder;
import com.wdq.chat.protocol.IMEncoder;
import com.wdq.chat.protocol.IMMessage;
import com.wdq.chat.protocol.IMProtocol;
import com.wdq.mq.MqProvider;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * IM协议处理
 * @Author: wudq
 * @Date: 2018/11/2
 */
@Component
public class IMProcessor {

    //记录在线用户数
    private static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public IMEncoder encoder = new IMEncoder();

    IMDecoder decoder = new IMDecoder();

    @Autowired
    private MqProvider mqProvider;

    /**
     * 发送消息，暴露给Handler的方法
     * @Author wudq
     * @Date 2018/11/02
     * @Param
    */
    public void sendMsg(Channel channel, String content) {
        send(channel, content);
    }

    /**
     * 发送消息的实现
     * @Author wudq
     * @Date 2018/11/02
     * @Param
    */
    private void send(Channel channel, String msg) {
        if(null == msg || msg.isEmpty()) {
            return;
        }

        IMMessage request = decoder.decode(msg);
        mqProvider.send();

        String nikeName = request.getSender();
        //判断消息指令
        if(request.getCmd().equals(IMProtocol.CHAT.getName())) {
            //两种遍历方式：1、迭代器iterator 2、for循环
            for (Channel ch : onlineUsers) {
                if (channel.equals(ch)) {

                } else {

                }
                //编码向外发送的消息
                String content = encoder.encode(request);
                ch.writeAndFlush(new TextWebSocketFrame(content));
            }
        } else if (request.getCmd().equals(IMProtocol.FLOWER.getName())) {

        } else if (request.getCmd().equals(IMProtocol.LOGIN.getName())) {
            //登录，加入在线组
            onlineUsers.add(channel);
            for (Channel ch : onlineUsers) {
                if (channel.equals(ch)) {
                    request = new IMMessage(request.getCmd(),systemTime(),
                            onlineUsers.size(),"已加入聊天室！");
                } else {
                    //TODO nikeName为空
                    request = new IMMessage(request.getCmd(),systemTime(),
                            onlineUsers.size(),nikeName+":加入了聊天室！");
                }
                request.setSender(nikeName);
                String content = encoder.encode(request);
                ch.writeAndFlush(new TextWebSocketFrame(content));
            }
        } else if (request.getCmd().equals(IMProtocol.LOGOUT.getName())) {
            //登出，
            onlineUsers.remove(channel);
        } else if (request.getCmd().equals(IMProtocol.SYSTEM.getName())) {

        }
    }

    private long systemTime() {
        return new java.util.Date().getTime();
    }
}