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

package com.moha.demo.controller;


import com.moha.demo.util.LifeBean;

public class Main {
    public static void main(String[] args) {
        String x=new String("+1111");
        String xs[] = x.split("-",3);
        System.out.println(xs);

        String xy=new String("+1111");
        System.out.println(x.hashCode());

        System.out.println(xy.hashCode());
        System.out.println(x.equals(xy));
        System.out.println(x==xy);


        LifeBean lifeBean = new LifeBean();
        LifeBean lifeBean1 = new LifeBean();
        System.out.println(lifeBean.hashCode());
        System.out.println(lifeBean1.hashCode());
        System.out.println(lifeBean.equals(lifeBean1));

        char a = '1';
        System.out.println(1+a);
    }
}
