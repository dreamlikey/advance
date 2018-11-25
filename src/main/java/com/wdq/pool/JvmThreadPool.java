package com.wdq.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wudq
 * @Date: 2018/10/31
 */
public class JvmThreadPool implements ThreadPool{

    private AtomicInteger poolSize;

    public static final int RUNNING = -1;

    static List<Thread> threadList = new ArrayList<Thread>();

    public JvmThreadPool(int poolSize) {
    }

    private void createThreads(Runnable runnable) {
        if(poolSize.get() <= threadList.size()) {
            //等待队列
            return;
        }
        threadList.add(new Thread(runnable));

    }

    public void execute(Runnable runnable) {

        createThreads(runnable);
    }

}