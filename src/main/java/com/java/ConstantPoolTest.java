package com.java;

/**
 * Created by hzzhaolong on 19/5/23.
 */
public class ConstantPoolTest {

    public static void main(String[] args) {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println("s1 == s2 ? " + (s1 == s2));           // false
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println("s3 == s4 ? " + (s3 == s4));           // true
        String s5 = "aaa";
        String s6 = "aaa";
        System.out.println("s5 == s6 ? " + (s5 == s6));  // true

    }

}
