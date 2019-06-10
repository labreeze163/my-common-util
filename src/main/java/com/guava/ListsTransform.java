package com.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by hzzhaolong on 19/5/31.
 */
public class ListsTransform {

    /**
     * 0. 源列表不能为空 否则会报npe
     * 1. 源列表发生变更  transform的列表也同步变更
     * 2. transform的列表是不可变的, 不能手动对transform进行修改,即使对象的属性也是不能重新设置的
     * @param args
     */
    public static void main(String[] args) {
        List<PersonDb> personDbs = null;
        List<PersonVo> personVos = Lists.transform(personDbs, new Function<PersonDb, PersonVo>() {

            public PersonVo apply(PersonDb personDb) {
                return personDbToVo(personDb);
            }
        });
        System.out.println(personVos);

        personDbs = Lists.newArrayList(new PersonDb("zhangsan", 10),
                new PersonDb("lisi", 20), new PersonDb("wangwu", 30));
        System.out.println(personVos);

        for(PersonDb personDb : personDbs) {
            personDb.setMsg("hello world!");
        }
        System.out.println(personVos);

        for(PersonVo personVo : personVos) {
            personVo.setMsg("Merry Christmas!");
        }
        System.out.println(personVos);

        personDbs.add(new PersonDb("sting", 40));
        System.out.println(personVos);

        personVos.add(personDbToVo(new PersonDb("hello", 50)));
        System.out.println(personVos);
    }

    public static PersonVo personDbToVo(PersonDb personDb) {
        PersonVo personVo = new PersonVo();
        personVo.setName(personDb.getName() + ",from Db");
        personVo.setAge(personDb.getAge());
        personVo.setMsg(personDb.getMsg());
        return personVo;
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


    static class PersonVo {
        private String name;
        private int age;
        private String msg;

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
