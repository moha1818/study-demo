package com.moha.demo.util;




public class PasswordIntensity {

    public static void main(String[] args) {
        String s = "123456789a";
        String s1 = "a987654321A";
        System.out.println(intensity(s,s1));
    }

    private static int intensity(String name, String password){
        if(password.length()<8){
            return 1;
        }else{
            if(name.equals(password)){
                return 1;
            }
            StringBuffer stringBuffer = new StringBuffer(password).reverse();
            if(name.equals(stringBuffer.toString())){
                return 1;
            }
            if(password.matches("^([A-Z0-9])+$") || password.matches("^([a-z0-9])+$")){
                return 2;
            }
            if(password.matches("^([A-Za-z0-9])+$")){
                return 3;
            }


        }
        return 1;
    }
}
