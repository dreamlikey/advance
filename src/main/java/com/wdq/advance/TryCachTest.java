package com.wdq.advance;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: wudq
 * @Date: 2018/10/26
 */
public class TryCachTest implements Serializable {

    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread());
        }
    });

    public static void  testTryCatch() {
        try {
            int a = 1;
            int b = 0;
            int c = a / b;
            System.out.println(b);
        } catch (Exception e) {
            System.out.println(1);
        } finally {
            Thread t = Thread.currentThread();
            System.out.println(t.isAlive());
            System.out.println(t);
        }
    }

    /**
     * ==比较地址
     * equals 比较内容
     * String作为一个对象使用 String str = new String();
     * String作为基本类型使用 String str = "hello";
     *
     *
    */
    public static void testString() {
        String str1 = "hello";
        String str2 = str1;
        String str3 = new String("hello");
        System.out.println(str1 == str2);   //true
        System.out.println(str1 == str3);   //false

        String str4 = "hello";

        System.out.println(str1 == str4);   //true
    }

    /**
     * 无异常：
     *  try中return后面的表达式先进行运算但不返回，执行finally中的代码之后再返回
     *  若finally中有return语句，则直接返回，不执行try中的return，不推荐这种用法
     *
     * 有异常：
     *  catch/finally中的代码依次运行，由于出现异常不执行try中return
     *
     *
    */
    public static int tryCatch() {
        try {
            int j = 1/1;
            return j;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("finally");
        }
        return 100;
    }


    public static void main(String[] args) {
//        testTryCatch();
//        thread1.start();
//        testString();
        System.out.println(tryCatch());
    }
}

class ThreadOne extends Thread{
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }
}