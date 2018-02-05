package com.moha.demo.util;

import org.apache.commons.validator.routines.EmailValidator;

public class CheckEmail {


    public static boolean checkEmailMethod(String email) {
        /*if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            System.err.println("Format error");
            return false;
        }*/
       return EmailValidator.getInstance().isValid(email);
    }

    public static void main(String[] args) {
        String email = "ssss";
        boolean f = checkEmailMethod(email);
        System.out.println(f);
    }
}