package com.jdk8;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by hzzhaolong on 19/7/11.
 */
public class LambdaTest {


    public static void main(String[] args) {

        // 使用现有的 函数式接口
        Runnable r1 = () -> System.out.println("hello,world");
        process(r1);

        Runnable r2 = () ->  {
            System.out.println("first Row");
            System.out.println("second Row");
        };
        process(r2);

        process(()-> System.out.println("success"));

        // 定义函数式接口
        List<String> originNames = Lists.newArrayList("wangchao", "mahan", "zhanglong", "zhaohu");
        List<String> filteredNames = filterName(originNames, (name) -> name.startsWith("wang") || name.startsWith("ma"));
        System.out.println("self define function :" + JSON.toJSONString(filteredNames));

        // 使用1.8的函数接口
        // predicate
        filteredNames = test(originNames, (String name) -> name.startsWith("wang") || name.startsWith("ma"));
        System.out.println("jdk define predicate :" + JSON.toJSONString(filteredNames));

        // consume
        System.out.print("jdk define consume begin:");
        foreach(originNames, (String name) -> System.out.print(name + " ~~~"));
        System.out.println();

        // function
        System.out.print("jdk define function begin:");
        List<Integer> numbers = plus1(Lists.newArrayList(1, 2, 3, 4, 5), (Integer number) -> number + 1);
        System.out.print(JSON.toJSONString(numbers));
        System.out.println();


        // 方法引用
        Account account = new Account();
        Supplier<String> getName = account::getName;
        System.out.println(getName.get());

        // 复合Lambda
    }

    private static void process(Runnable runnable) {
        runnable.run();
    }


    private static List<String> filterName(List<String> origin, MyFilter filter) {
        List<String> retList = Lists.newArrayList();

        for(String string: origin) {
            if(filter.filter(string)) {
                retList.add(string);
            }
        }

        return retList;
    }


    @FunctionalInterface
    public interface MyFilter {
        boolean filter(String str);
    }

    public static <T> List<T> test(List<T> tList, Predicate<T> predicate) {
        List<T> retList = Lists.newArrayList();
        for(T t: tList) {
            if(predicate.test(t)) {
                retList.add(t);
            }
        }
        return retList;
    }

    public static <T> void foreach(List<T> tList, Consumer<T> consumer) {
        for(T t: tList) {
            consumer.accept(t);
        }
    }

    public static <T extends Number, R extends Number> List<R> plus1(List<T> numbers, Function<T, R> function) {

        List<R> retList = Lists.newArrayList();
        for(T number: numbers) {
            retList.add(function.apply(number));
        }
        return retList;
    }


    static class Account {

        private String name;

        private String pass;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }

}
