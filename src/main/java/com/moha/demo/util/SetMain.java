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

package com.moha.demo.util;


import java.util.*;

public class SetMain {
    public static void main(String[] args) {


        Random r = new Random();

        HashSet<Dog> hashSet = new HashSet<Dog>();
        TreeSet<Dog> treeSet = new TreeSet<Dog>();
        LinkedHashSet<Dog> linkedSet = new LinkedHashSet<Dog>();



        // start time
        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            int x = r.nextInt(1000 - 10) + 10;
            hashSet.add(new Dog(x));
        }
        // end time
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("HashSet: " + duration);

        // start time
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int x = r.nextInt(1000 - 10) + 10;
            treeSet.add(new Dog(x));
        }
        // end time
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("TreeSet: " + duration);

        // start time
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int x = r.nextInt(1000 - 10) + 10;
            linkedSet.add(new Dog(x));
        }
        // end time
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("LinkedHashSet: " + duration);

        // start time
        startTime = System.nanoTime();
        Iterator<Dog> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // end time
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("HashSetget: " + duration);


        startTime = System.nanoTime();
        iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // end time
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("TreeSetget: " + duration);


        startTime = System.nanoTime();
        iterator = linkedSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // end time
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("LinkedHashSetget: " + duration);


    }
}
