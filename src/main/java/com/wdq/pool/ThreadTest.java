package com.wdq.pool;

import java.util.concurrent.Executors;

/**
 * @Author: wudq
 * @Date: 2018/10/31
 */
public class ThreadTest<thread> implements Runnable {

    private final Thread thread;

    private Runnable firstTask;

    public ThreadTest(Runnable firstTask) {
        this.firstTask = firstTask;
        this.thread = Executors.defaultThreadFactory().newThread(this);
    }

    public void run() {
        System.out.println("111===111");
    }


}