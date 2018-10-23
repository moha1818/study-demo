package com.moha.demo.controller;


import com.moha.demo.entity.Demo;

import java.util.HashSet;
import java.util.Set;

public class SetMain {

    public static void main(String[] args) {
        Set<Demo> set = new HashSet<Demo>();
        Demo vo = new Demo(1, "sh");
        Demo vo1 = new Demo(2, "bj");
        Demo vo2 = new Demo(3, "sh");

        set.add(vo);
        set.add(vo1);
        set.add(vo2);

        System.out.println(set);
    }
}
