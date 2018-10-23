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

import java.util.Random;

public class Ticket4 implements Runnable {
    // 当前拥有的票数
    //AtomicInteger num = new AtomicInteger(100);

    private int num = 0;
    @Override
    public void run() {
        while (num<70) {
            int t = new Random().nextInt(10)+1;
            num = num + t;
            System.out.println(num);
            }
        }
}
