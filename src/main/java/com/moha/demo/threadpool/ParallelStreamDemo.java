
package com.moha.demo.threadpool;


import java.util.ArrayList;
import java.util.concurrent.atomic.LongAdder;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        LongAdder sum = new LongAdder();
        System.out.println(System.currentTimeMillis());
        list.parallelStream().forEach(integer -> {
//            System.out.println("当前线程" + Thread.currentThread().getName());
            sum.add(integer);
        });
        System.out.println(System.currentTimeMillis());
        int a = 0;
        for(Integer i:list){
            a+=i;
        }
        System.out.println(System.currentTimeMillis());

        System.out.println(sum);
    }
}
