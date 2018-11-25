package com.wdq.chat.socket;

import java.io.Serializable;

/**
 * @Author: wudq
 * @Date: 2018/10/26
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -4074588768957638900L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}