package com.wdq.advance;

import java.util.concurrent.*;

/**
 * @Author: wudq
 * @Date: 2018/10/23
 */
public class Threads {
    
    static Callable<String> callable1 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("callable1");
            return "callable1";
        }
    };
    static Callable<String> callable2 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("callable2");
            return "callable2";
        }
    };
    static Callable<String> callable3 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.println("callable3");
            return business("callable3");
        }
    };

    public static String business(String name) {
        return "business:"+name;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String a ;
        long startTime = System.currentTimeMillis();
        FutureTask<String> task1 = new FutureTask<String>(callable1);
        FutureTask<String> task2 = new FutureTask<String>(callable2);
        FutureTask<String> task3 = new FutureTask<String>(callable3);
//        Thread thread1 = new Thread(task1);
//        thread1.start();
//        Thread thread2 = new Thread(task2);
//        thread2.start();
//        Thread thread3 = new Thread(task3);
//        thread3.start();


        //get()线程阻塞，线程等待直到获取返回值继续运行
//        System.out.println("---"+task1.get());
//        System.out.println("----" + task2.get());
//        System.out.println("----" + task3.get());


        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> res1 = service.submit(callable1);
        System.out.println("res1");
        Future<String> res2 = service.submit(callable2);
        System.out.println("res2");
        Future<String> res3 = service.submit(callable3);
        System.out.println("res3");



        System.out.println("---"+ res1.get());
        System.out.println("----" + res2.get());
        System.out.println("----" + res3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("总的线程执行时间："+ (endTime - startTime));
        service.shutdown();
    }
}