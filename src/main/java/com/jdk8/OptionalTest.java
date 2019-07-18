package com.jdk8;

import java.util.Optional;

/**
 * Created by hzzhaolong on 19/7/18.
 *
 * Optional实际上是个容器：它可以保存类型T的值，或者仅仅保存null
 *
 */
public class OptionalTest {

    public static void main(String[] args) {

        // 构造optional

        // 参数不能是null 否则npe
//        Optional<Integer> optional1 = Optional.of(null);
        Optional<Integer> optional1 = Optional.of(1);
        System.out.println(" Optional.of() :" + optional1.isPresent());

        Optional<Integer> optional2 = Optional.ofNullable(null);
        System.out.println(" Optional.ofNullable() :" + optional2.isPresent());

        Optional<Integer> optional3 = Optional.ofNullable(3);
        System.out.println(" Optional.ofNullable() :" + optional3.isPresent());

        // isPresent

        // ifPresent 如果option对象保存的值不是null，则调用consumer对象，否则不调用
        System.out.println("==========================");
        optional1.ifPresent((Integer data) -> System.out.println("optional1 :" + data));
        optional2.ifPresent((Integer data) -> System.out.println("optional2 :" + data));
        optional3.ifPresent((Integer data) -> System.out.println("optional3 :" + data));

        // orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
        System.out.println("==========================");
        System.out.println("optional1 :" + optional1.orElse(10).toString());
        System.out.println("optional2 :" + optional2.orElse(20).toString());
        System.out.println("optional3 :" + optional3.orElse(30).toString());

        // orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
        System.out.println("==========================");
        System.out.println("optional1 :" + optional1.orElseThrow(() -> {return new RuntimeException();}));
        try {
            System.out.println("optional2 :" + optional2.orElseThrow(() -> {return new RuntimeException();}));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("optional3 :" + optional3.orElseThrow(() -> {return new RuntimeException();}));

        // filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional
        System.out.println("==========================");
        System.out.println("optional1 :" + optional1.filter((Integer data) -> data != null && data == 1).isPresent());
        System.out.println("optional2 :" + optional2.filter((Integer data) -> data != null && data == 1).isPresent());
        System.out.println("optional3 :" + optional3.filter((Integer data) -> data != null && data == 1).isPresent());


        // map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
        System.out.println("==========================");
        System.out.println("optional1 :" + optional1.map((Integer data) -> null != data ? data + 100 : -1).isPresent());
        System.out.println("optional2 :" + optional2.map((Integer data) -> null != data ? data + 100 : -1).isPresent());
        System.out.println("optional3 :" + optional3.map((Integer data) -> null != data ? data + 100 : -1).isPresent());


    }

}
