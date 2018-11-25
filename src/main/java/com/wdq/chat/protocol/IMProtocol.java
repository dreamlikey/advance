package com.wdq.chat.protocol;

/**
 * 自定义IM协议
 * Instant Message Protocol即时通讯协议
 * @Author: wudq
 * @Date: 2018/11/2
 */
public enum IMProtocol {
    SYSTEM("SYSTEM"),
    CHAT("CHAT"),
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    FLOWER("FLOWER");

    private String name;

    //判断是否是IM协议
    public static boolean isMP(String message) {
        return message.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER)\\]");
    }

    IMProtocol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}