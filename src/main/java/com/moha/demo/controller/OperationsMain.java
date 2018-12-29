
package com.moha.demo.controller;


public class OperationsMain {

    public static void main(String[] args) {

        for(int i = 0; i < 9; i++) {
            int value = 1 << i;
            System.out.println(value);
            System.out.println(384 & value);
        }
    }
}
