package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzzhaolong on 2016/5/9.
 */
public class TestReg {

    public static void main(String[] args) {

        String regEx = "[+-`]";
        Pattern   p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher("labree+123-456@163.com");
        System.out.println(m.replaceAll("").trim());
    }
}

