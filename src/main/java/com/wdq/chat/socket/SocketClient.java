package com.wdq.chat.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author: wudq
 * @Date: 2018/10/26
 */
public class SocketClient {

    private static final String SOCKET_ADDRESS = "localhost";
    private static final int PORT = 8008;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SOCKET_ADDRESS,PORT);
            OutputStream os = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(os);
            writer.write("客户端测试socket");
            writer.flush();
            socket.shutdownOutput();
            socket.shutdownOutput();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务端说："+info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}