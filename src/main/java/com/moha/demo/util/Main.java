

package com.moha.demo.util;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static String  sinaAppKey = "2849184197";
    private static String  sinaDomain = "http://api.weibo.com/2/short_url/shorten.json";
    public static void main(String[] args) {
        //String re = createShortUrl("https://qr.alipay.com/bax03049isqc0hv4qhjt608d");
        String ss = bit("https://qr.alipay.com/bax03049isqc0hv4qhjt608d");
       // System.out.println(re);
        System.out.println(ss);
    }
    private static String createShortUrl(String url) {

        String param = "source=" + sinaAppKey + "&url_long=" + url.replaceAll("&", "%26");
        String result = HttpRequest.sendGet(sinaDomain, param);

        return result;
    }

    private static  String bit(String url){
        String s = HttpRequest.sendPost("https://api-ssl.bitly.com/v4/bitlinks","long_url=https://qr.alipay.com/bax03049isqc0hv4qhjt608d");
        return s;
    }
}
