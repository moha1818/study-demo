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


import com.moha.demo.entity.Demo;
import com.moha.demo.util.LifeBean;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

        Map<Object,String> map = new Hashtable<Object, String>();
        map.put(new Dog("1"),"1");
        map.put(new Dog("1"),"2");
        map.put(new Dog("2"),"3");
        map.put(new Dog("3"),"1");

        for(Map.Entry<Object,String> entry : map.entrySet()){
            System.out.println(entry.getKey().toString() + " - " + entry.getValue());
        }

        LifeBean lifeBean = new LifeBean();
        LifeBean lifeBean1 = new LifeBean();
        System.out.println(lifeBean.hashCode());
        System.out.println(lifeBean1.hashCode());
        System.out.println(lifeBean.equals(lifeBean1));

        char a = '1';
        System.out.println(1+a);

        List<Demo> list = Arrays.asList(new Demo(1,"1"),new Demo(2,"2"),new Demo(3,"afssaf <img id=\"img1\" src=\"images/picture1.png\" onclick=\"change()\"> fsafsaf"));
        List<String> lista = list.stream().map(demo -> demo.getName()).filter(q->!"".equals(q)).collect(Collectors.toList());

        lista = lista.stream().map(q->q = q.replaceAll("<img(?:.|\\s)*?>","")).collect(Collectors.toList());

        String se = "afssaf <img id=\"img1\" src=\"images/picture1.png\" onclick=\"change()\"> fsafsaf";
        se = se.replaceAll("<img(?:.|\\s)*?>","");

        String ssa = "大型机械及设备大型机械及设备\n" +
                "                                                        \n" +
                "                                                            小型机械小型机械\n" +
                "                                                        \n" +
                "                                                         ";


        System.out.println(ssa.replaceAll("\\n" ,""));





        String email = "www@qq.com";
        String[] param = email.split("@");
        param[0] = "***";


        SimpleDateFormat sdfelse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println(System.currentTimeMillis());

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.MINUTE));


    }

}
