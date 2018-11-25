package com.wdq.chat.protocol;

import java.util.Date;

/**
 * 消息类--网络上只传输，约定的协议内容数据，不暴露消息类在网络上
 * @Author: wudq
 * @Date: 2018/11/2
 */
public class IMMessage {

    private String addr;        //地址
    private String sender;      //消息发送者
    private String receiver;    //消息接收者
    private String content;     //消息内容
    private String cmd;         //消息指令
    private long time;          //发送时间
    private int online;		    //当前在线人数

    public IMMessage() {
    }

    public IMMessage(String cmd, long time, int online, String content){
        this.cmd = cmd;
        this.time = time;
        this.online = online;
        this.content = content;
    }
    public IMMessage(String cmd,long time,String sender){
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
    }
    public IMMessage(String cmd,long time,String sender,String content){
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
        this.content = content;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}