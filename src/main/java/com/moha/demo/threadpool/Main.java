package com.moha.demo.threadpool;

public class Main {
    public static void main(String[] args) {
        /*Ticket t = new Ticket();//创建一个线程任务对象。
        //创建4个线程同时卖票
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();*/
        //t1();
        int x=8;
        if(x!=7 || x!=8){
            System.out.println(1);
        }
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
