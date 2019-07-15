package com.java;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzzhaolong on 19/5/23.
 *
 * 1. 泛型类
 * 2. 泛型接口
 * 3. 泛型方法
 *
 * // 泛型通配符
 *  通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
 *
 * // 泛型上下边界
 * ? extends T  入参必须是T的子类
 * ? super T 入参必须是T的父类
 *
 */
public class GenericTypeTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("aaaa");
        list.add(100);

        System.out.println(list.get(0));
        System.out.println(list.get(1));

        GenericClass<Integer> genericClass = new GenericClass<Integer>(123);
        System.out.println(genericClass.getT());

        GenericTypeInterface<Integer> genericTypeInterface = new GenericInterfaceImpl();
        System.out.println(genericTypeInterface.next());

        GenericMethodClass genericMethodClass = new GenericMethodClass();
        genericMethodClass.print("hello , world!");
    }

    static class GenericClass<T> {
        private T t;

        public GenericClass(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    static class GenericInterfaceImpl implements GenericTypeInterface<Integer> {

        public Integer next() {
            return 100;
        }
    }

//    static class GenericInterfaceImpl2 implements GenericTypeInterface<String> {
//
//        public String next() {
//            return String.valueOf("abcd");
//        }
//    }

    static class GenericMethodClass {

        public <T> void print(T t) {
            System.out.println("generic method call :" + t);
        }

    }
}
