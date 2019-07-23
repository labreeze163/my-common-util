package com.jdk8;

/**
 * Created by hzzhaolong on 19/7/18.
 */
public interface InterfaceTest {

    default void sayHello() {
        System.out.println("hello");
    }
}
