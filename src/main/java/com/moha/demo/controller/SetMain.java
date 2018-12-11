package com.moha.demo.controller;


import com.moha.demo.entity.Demo;
import com.moha.demo.entity.View;

import java.util.*;
import java.util.stream.Collectors;

public class SetMain {

    public static void main(String[] args) {
        //去重 1
        Set<Demo> set = new HashSet<Demo>();
        Demo vo = new Demo(1, "sh");
        Demo vo1 = new Demo(2, "bj");
        Demo vo2 = new Demo(3, "sh");

        set.add(vo);
        set.add(vo1);
        set.add(vo2);

        System.out.println(set);

        //去重 2
        View veo = new View(1, "sh");
        View veo1 = new View(2, "bj");
        View veo2 = new View(1, "sh");

        List<View> views = Arrays.asList(veo,veo1,veo2);
        List<View> unique = views.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(View::getId))), ArrayList::new)
        );
        unique.forEach(p -> System.out.println(p.getId()));

        System.out.println(unique);

    }
}
