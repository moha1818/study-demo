package com.moha.demo.util;


import java.util.ArrayList;
import java.util.List;

public class LifeBean {
    private String name;
    public List<String> texts = new ArrayList<String>();

    public LifeBean(){
        System.out.println("LifeBean()构造函数");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName()");
        this.name = name;
    }

    public void init(){
        texts.clear();
        System.out.println("this is init of lifeBean");
    }

    public List<String> product(){
        System.out.println("this is run of lifeBean");
        return texts;
    }

    public void destory(){
        System.out.println("this is destory of lifeBean " + this);
    }
}
