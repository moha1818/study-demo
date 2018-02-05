package com.moha.demo.threadpool;

public class Ticket2 implements Runnable {
    // 当前拥有的票数
    private int num = 100;
    @Override
    public void run() {
        while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            synchronized (this) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
                    System.out.println(num+" time:"+System.currentTimeMillis());
                } else {
                    break;
                }
            }
        }
    }
}
