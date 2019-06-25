package com.moha.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moha.demo.util.Encript;
import com.moha.demo.util.HttpRequest;
import com.moha.demo.util.HttpURLUtil;

public class Shorturl {
    private static String  sinaAppKey = "2937462991";
    private static String  sinaDomain = "http://api.weibo.com/2/short_url/shorten.json";
    public static void main(String[] args) {
        String re = createShortUrl("https://qr.alipay.com/bax03049isqc0hv4qhjt608d");
        System.out.println(re);
    }
    private static String createShortUrl(String url) {

       String param = "source=" + sinaAppKey + "&url_long=" + url.replaceAll("&", "%26");
        String result = HttpRequest.sendGet(sinaDomain, param);
        /*JSONArray urls = jsonObject.getJSONArray("urls");
        for (int i = 0; i < urls.length(); i++) {
            JSONObject jsonObjecUrls = urls.getJSONObject(i);
            url_short = jsonObjecUrls.getString("url_short");
        }*/
        return result;
    }


    public static String[] ShortText(String string){
        String key = "MOHA";                 //自己定义生成MD5加密字符串前的混合KEY
        String[] chars = new String[]{          //要使用生成URL的字符
                "a","b","c","d","e","f","g","h",
                "i","j","k","l","m","n","o","p",
                "q","r","s","t","u","v","w","x",
                "y","z","0","1","2","3","4","5",
                "6","7","8","9","A","B","C","D",
                "E","F","G","H","I","J","K","L",
                "M","N","O","P","Q","R","S","T",
                "U","V","W","X","Y","Z"
        };

        String hex = Encript.md5(key + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }

        return ShortStr;
    }

    private static void print(Object messagr){
        System.out.println(messagr);
    }
}
