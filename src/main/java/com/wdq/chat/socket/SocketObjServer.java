package com.wdq.chat.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author: wudq
 * @Date: 2018/10/26
 */
public class SocketObjServer {
    private static final String SOCKET_ADDRESS = "localhost";
    private static final int PORT = 8008;
    public static void main(String[] args) {
        //启动一个socket服务
        SocketAddress socketAddress = new InetSocketAddress(SOCKET_ADDRESS, PORT);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(socketAddress);
            while (true) {
                //阻塞，等待请求
                Socket socket = serverSocket.accept();
                //获取server 输入流
                PrintWriter writer = new PrintWriter((socket.getOutputStream()));
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                UserDTO user = (UserDTO) ois.readObject();

                System.out.println("服务端接收到数据："+user.toString());
                //响应客户端
                writer.write("服务端收到请求，欢迎客户端！");
                writer.flush();
                //坑：：：：：：：
                //输入、输出流使用完之后，调用此方法，确认数据输入输出结束。
                //不调用此方法，输入输出数据流得不到确认，会造成socket的服务端、客户端阻塞
//                socket.shutdownInput();
                socket.shutdownOutput();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}