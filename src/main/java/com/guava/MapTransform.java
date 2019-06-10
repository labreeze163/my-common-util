package com.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hzzhaolong on 19/6/10.
 */
public class MapTransform {

    public static void main(String[] args) {
        List<PersonDb> personDbs = Lists.newArrayList(new PersonDb("zhangsan", 10),
                new PersonDb("lisi", 20), new PersonDb("wangwu", 30));
        ImmutableMap<String, PersonDb> stringPersonDbImmutableMap = Maps.uniqueIndex(personDbs, new Function<PersonDb, String>() {
            public String apply(PersonDb input) {
                return input.getName();
            }
        });
        System.out.println(stringPersonDbImmutableMap);

        personDbs.add(new PersonDb("lisi", 40));
        ImmutableListMultimap<String, PersonDb> multimap = Multimaps.index(personDbs, new Function<PersonDb, String>() {
            public String apply(PersonDb input) {
                return input.getName();
            }
        });
        System.out.println(multimap);

        for (Map.Entry<String, PersonDb> entry : multimap.entries()) {
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        }

        for (String key : multimap.keySet()) {
            System.out.println(key + "++++++++" + multimap.get(key));
        }

    }



    static class PersonDb {
        private String name;
        private int age;
        private String msg;
        public PersonDb(String name, int age){
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
