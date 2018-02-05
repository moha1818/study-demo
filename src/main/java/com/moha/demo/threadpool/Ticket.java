package com.moha.demo.threadpool;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    // 当前拥有的票数
    private int num = 100;
    static ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                // 输出卖票信息
                lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
            }else {
                break;
            }
                lock.unlock();
        }
    }
}
