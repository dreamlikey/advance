package com.wdq.advance;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wudq
 * @Date: 2018/10/23
 */
public class QueueThread {

    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread2");
        }
    });

    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread3");
        }
    });

    public static void main(String[] args) throws InterruptedException {
        //线程并不是顺序执行
        //使用join方法达到线程顺序执行
        //主线程等待子线程运行完后才能继续运行
//        thread1.start();
//        thread1.join();
//        thread2.start();
//        thread2.join();
//        thread3.start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);

        executorService.shutdown();

    }
}