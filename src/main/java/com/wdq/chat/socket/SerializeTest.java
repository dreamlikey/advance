package com.wdq.chat.socket;

import java.io.*;

/**
 * @Author: wudq
 * @Date: 2018/10/26
 */
public class SerializeTest {
    public static void main(String[] args) throws Exception{
        UserDTO user = new UserDTO();
        user.setName("wew");
        user.setAge(121);
        serialize(user);
        unSerialize();

    }
    public static void serialize(UserDTO user) throws Exception{
        OutputStream os = new FileOutputStream("/D:User.text");
        ObjectOutputStream ois = new ObjectOutputStream(os);
        ois.writeObject(user);
        System.out.println("序列化成功:");
    }

    public static void unSerialize() throws  Exception{
        InputStream is = new FileInputStream("/D:User.text");
        ObjectInputStream ois = new ObjectInputStream(is);
        UserDTO user = (UserDTO) ois.readObject();
        user.toString();
        System.out.println("--反序列化成功");
    }
}