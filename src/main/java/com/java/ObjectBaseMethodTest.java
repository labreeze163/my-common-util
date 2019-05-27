package com.java;

/**
 * Object类的几个基础方法
 * Created by hzzhaolong on 19/5/23.
 */
public class ObjectBaseMethodTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneExample cloneExample = new CloneExample();
        Object clone = cloneExample.clone();
        System.out.println(clone);

        CloneExample cloneExample2 = new CloneExample();
        Object clone2 = cloneExample2.clone();
        System.out.println(clone2);
    }

    static class CloneExample {
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class CloneExample2 implements Cloneable {
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
