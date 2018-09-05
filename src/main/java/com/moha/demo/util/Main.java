

package com.moha.demo.util;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext container =
                new ClassPathXmlApplicationContext("config/spring.xml");
        LifeBean life1 = (LifeBean)container.getBean("life_singleton");
        LifeBean life2 = (LifeBean)container.getBean("life_singleton");
        System.out.println(life1);
        System.out.println(life2);
        container.close();
    }
}
