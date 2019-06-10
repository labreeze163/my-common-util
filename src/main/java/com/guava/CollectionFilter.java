package com.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by hzzhaolong on 19/5/31.
 */
public class CollectionFilter {

    public static void main(String[] args) {

        List<Person> persons = null;
        persons = Lists.newArrayList();
        Collection<Person> filter = Collections2.filter(persons, new Predicate<Person>() {
            public boolean apply(Person input) {
                return input.getAge() > 10;
            }
        });
        System.out.println(filter);

        persons = Lists.newArrayList(new Person("zhangsan", 10),
                new Person("lisi", 20), new Person("wangwu", 30));
        System.out.println(filter);

        filter = Collections2.filter(persons, new Predicate<Person>() {
            public boolean apply(Person input) {
                return input.getAge() > 10;
            }
        });
        System.out.println(filter);
    }

    static class Person {
        private String name;
        private int age;
        private String msg;
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }
}



