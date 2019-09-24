package com.moha.demo.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownTaskTest  高并发
 * @Author MoHa
 * @Description TODO
 * @Date 2019-09-24 16:23
 **/
public class CountDownTaskTest {

    static class MyThread implements Runnable {
        private final CountDownLatch startSignal;
        public MyThread(CountDownLatch startSignal) {
            super();
            this.startSignal = startSignal;
        }
        @Override
        public void run() {
            try {
                startSignal.await();
                //一直阻塞当前线程，直到计时器的值为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //实际测试操作
            doWork();
        }
        private void doWork() {
            // TODO Auto-generated method stub
            System.out.println("do work");
        }
    }

    public static void main(String[] args) {
        // 初始化计数器为 1
        CountDownLatch start=new CountDownLatch(1);
        //模擬16个线程
        for(int i=0;i<16;i++){
            MyThread tt =new MyThread(start);
            Thread t = new Thread(tt);
            t.start();
        }
        //计数器減 1
        start.countDown();
        //计数器为0，所有线程释放，同时并发
    }
}
