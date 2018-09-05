package com.moha.demo.threadpool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Main {

    private static ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    public static void main(String[] args) {
        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        Ticket2 t = new Ticket2();//创建一个线程任务对象。

        for(int i=0;i<=4;i++){
            threadPoolTaskExecutor.submit(t);
        }

        /*for(int i=0;i<=4;i++){
            new Thread(t).start();
        }*/

        //创建4个线程同时卖票
       /* Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);*/
        //启动线程
      /*  t1.start();
        t2.start();
        t3.start();
        t4.start();*/
        //t1();
        /*int x=8;
        if(x!=7 || x!=8){
            System.out.println(1);
        }*/
    }

    private static void t1(){
        System.out.println("t1");
        t2();
    }

    private static void t2(){
        System.out.println("t2");
        t3();
    }
    private static void t3(){
        System.out.println("t3");
        t1();
    }
}
