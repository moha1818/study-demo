
package com.moha.demo.controller;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Combine {
        public static void main(String[] args) {
            List listA = new ArrayList<>();
            listA.add("a");
            listA.add("b");
            listA.add("c");
            listA.add("d");
            listA.add("e");
            listA.add("f");
            List listB = new ArrayList<>();
            listB.add("d");
            listB.add("e");
            listB.add("f");
            listB.add("g");
            listB.add("h");
            Set set = new HashSet<>(listA);
            set.addAll(listB);
            List list = new ArrayList<>(set);
            System.out.println(list);
            List collect = Stream.of(listA, listB)
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(collect);
            listA.removeAll(listB);

            System.out.println(listA);
        }
}
