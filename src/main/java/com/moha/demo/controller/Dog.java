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


public class Dog {
    String color;

    Dog(String c) {
        color = c;
    }

    public boolean equals(Object o) {
        return ((Dog) o).color.equals(this.color);
    }

    public int hashCode() {
        return color.length();
    }

    public String toString(){
        return color + " dog";
    }
}
