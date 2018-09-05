/*
 * 版权所有(C) 浙江大道网络科技有限公司2011-2020
 * Copyright 2009-2020 Zhejiang GreatTao Factoring Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Zhejiang GreatTao Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Zhejiang GreatTao
 */

package com.moha.demo.threadpool;


import java.util.concurrent.locks.ReentrantLock;

public class TestTask {
    public volatile int inc = 0;
    static ReentrantLock lock = new ReentrantLock(true);
    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final TestTask test = new TestTask();
        for(int i=0;i<10;i++){
            new Thread(){
                    @Override
                    public void run() {
                        for(int j=0;j<1000;j++) {
                            lock.lock();
                            test.increase();
                            lock.unlock();
                        }
                    }

            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
        {
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}