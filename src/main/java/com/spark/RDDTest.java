package com.spark;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hzzhaolong on 19/7/4.
 *
 * ref:https://my.oschina.net/yulongblog/blog/1509506
 */
public class RDDTest {


    public static void main(String[] args) throws IOException {
        SparkConf sparkConf= new SparkConf()
                .setAppName("App Application")
                .setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        // 1. from file
        // eg: hdfs:///data/README.md   file:///data/README.md
        // String logPath = new File("..").getCanonicalPath() + "/my-common-util/src/main/resources/spark-testdata1.txt";
        // JavaRDD<String> logData = sparkContext.textFile(logPath).cache();

        // 2. from List set
        List<String> logList = Lists.newArrayList("a123", "b1234", "123a", "444");
        JavaRDD<String> logData = sparkContext.parallelize(logList);

        // Transformations -> filter
        JavaRDD<String> filteredLogData = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("a");
            }
        });

        System.out.println("==============>");
        List<String> collectStrings = filteredLogData.collect();
        for(String string: collectStrings) {
            System.out.println(string);
        }
        System.out.println("<==============");

        // Transformations -> mapToPair groupByKey
        List<Student> students = Lists.newArrayList(
                new Student("zhao", "xiaohua", 0), new Student("wang", "dama", 0), new Student("zhao", "2", 1),
                new Student("wang", "wangwang", 0), new Student("li", "ming", 1), new Student("zhao", "2", 1)
        );
        JavaRDD<Student> parallelizeStudents = sparkContext.parallelize(students);

        JavaPairRDD<String, Iterable<Student>> firstNameGroupRDD = parallelizeStudents.mapToPair(new PairFunction<Student, String, Student>() {

            public Tuple2<String, Student> call(Student input) throws Exception {
                return new Tuple2<String, Student>(input.getFirstName(), input);
            }
        }).groupByKey();

        System.out.println("==============>");

        Map<String, Iterable<Student>> firstNameGroupMap = firstNameGroupRDD.collectAsMap();
        for (Map.Entry<String, Iterable<Student>> entry : firstNameGroupMap.entrySet()) {
            System.out.print(entry.getKey() + " [");
            for(Student student: entry.getValue()) {
                System.out.print(JSONObject.toJSONString(student));
            }
            System.out.println("]");
        }

        System.out.println("<==============");

        // Transformations -> map
        JavaRDD<JSONObject> jsonStudentRDD = parallelizeStudents.map(new Function<Student, JSONObject>() {
            public JSONObject call(Student input) throws Exception {
                JSONObject jsonO = new JSONObject();
                jsonO.put("firstName", input.getFirstName());
                jsonO.put("lastName", input.getLastName());
                return jsonO;
            }
        });

        System.out.println("==============>");

        for (JSONObject jsonObject : jsonStudentRDD.collect()) {
            System.out.println(jsonObject.toJSONString());
        }
        System.out.println("<==============");

        // Transformations -> flatMap
        JavaRDD<JSONObject> jsonObjectJavaRDD = parallelizeStudents.flatMap(new FlatMapFunction<Student, JSONObject>() {
            public Iterator<JSONObject> call(Student input) throws Exception {
                List<JSONObject> jsonObjects = Lists.newArrayList();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("firstName", input.getFirstName());
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("lastName", input.getLastName());
                jsonObjects.add(jsonObject1);
                jsonObjects.add(jsonObject2);
                return jsonObjects.iterator();
            }
        });
        System.out.println("==============>");

        for (JSONObject jsonObject : jsonObjectJavaRDD.collect()) {
            System.out.println(jsonObject.toJSONString());
        }
        System.out.println("<==============");


        // Transformations -> mapPartitions
        parallelizeStudents = sparkContext.parallelize(students, 2);
        JavaRDD<Integer> sexJavaRdd = parallelizeStudents.mapPartitions(new FlatMapFunction<Iterator<Student>, Integer>() {
            public Iterator<Integer> call(Iterator<Student> studentIterator) throws Exception {
                List<Integer> sexList = Lists.newArrayList();
                while (studentIterator.hasNext()) {
                    Student student = studentIterator.next();
                    sexList.add(student.getSex());
                }
                return sexList.iterator();
            }
        });
        System.out.println("==============>");

        for (Integer sex : sexJavaRdd.collect()) {
            System.out.println(sex);
        }
        System.out.println("<==============");

        // Transformations -> mapPartitionsWithIndex
        sexJavaRdd = parallelizeStudents.mapPartitionsWithIndex(new Function2<Integer, Iterator<Student>, Iterator<Integer>>() {
            public Iterator<Integer> call(Integer v1, Iterator<Student> v2) throws Exception {
                if (v1.intValue() == 1) {
                    List<Integer> sexList = Lists.newArrayList();
                    while (v2.hasNext()) {
                        Student student = v2.next();
                        sexList.add(student.getSex());
                    }
                    return sexList.iterator();
                }
                return new ArrayList<Integer>().iterator();
            }
        }, false);

//        List<Integer> partion1SexList = sexJavaRdd.collectPartitions(new int[]{1})[0];

        List<Integer> partion1SexList = sexJavaRdd.collect();
        System.out.println("==============>");

        for (Integer sex : partion1SexList) {
            System.out.println(sex);
        }
        System.out.println("<==============");


        // Transformations -> sample
        JavaRDD<Student> sampleRDD = parallelizeStudents.sample(true, 0.5);
        System.out.println("==============>");

        for (Student student : sampleRDD.collect()) {
            System.out.println(JSONObject.toJSONString(student));
        }
        System.out.println("<==============");


        // Transformations -> distinct
        // distinct uses the hashCode and equals method of the objects for this determination.
        JavaRDD<Student> distinctRDD = parallelizeStudents.distinct();
        System.out.println("==============>");
        System.out.println("distinctRDD.count():" + distinctRDD.collect().size());
        System.out.println("<==============");

        // Transformations groupByKey 先移动相同key的数据到相同机器 然后聚合
        // Transformations reduceByKey 先聚合再移动到相同机器聚合

        // Transformations aggregateByKey

        // Action aggregate
        // 第一个函数每个分区的元素如何聚合  第二个函数每个分区的结果如何聚合
        // https://www.jianshu.com/p/6825914cc26f
        JavaRDD<Integer> numberJavaRDD = sparkContext.parallelize(Lists.<Integer>newArrayList(5, 1, 1, 4, 4, 2, 2), 3);
        Integer aggregateValue = numberJavaRDD.aggregate(3, new Function2<Integer, Integer, Integer>() {

            public Integer call(Integer v1, Integer v2) throws Exception {
                System.out.println("seq~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + v1 + "," + v2);
                return Math.max(v1, v2);
            }
        }, new Function2<Integer, Integer, Integer>() {
            int i = 0;

            public Integer call(Integer v1, Integer v2) throws Exception {
                System.out.println("comb~~~~~~~~~i~~~~~~~~~~~~~~~~~~~"+i++);
                System.out.println("comb~~~~~~~~~v1~~~~~~~~~~~~~~~~~~~" + v1);
                System.out.println("comb~~~~~~~~~v2~~~~~~~~~~~~~~~~~~~" + v2);
                return v1 + v2;
            }
        });
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+aggregateValue);


        // Action reduce
        Integer reduceNumber = numberJavaRDD.reduce(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        System.out.println("reduceNumber = " + reduceNumber);

        // Action collect
        // 在驱动程序中，以数组的形式返回数据集的所有元素。
        // 通常用于filter或其它产生了大量小数据集的情况。

        // Action first
        System.out.println("Action first = " + numberJavaRDD.first());

        // Action take(n) 返回数据集中的前n个元素
        System.out.println("Action taken = " + numberJavaRDD.take(2));

        // Action countByKey
        // 统计key出现的次数
        Map<String, Long> stringLongMap = numberJavaRDD.mapToPair(new PairFunction<Integer, String, Integer>() {
            public Tuple2<String, Integer> call(Integer integer) throws Exception {
                return new Tuple2<String, Integer>(integer.toString(), integer);
            }
        }).countByKey();
        System.out.println("stringLongMap = " + stringLongMap);


        sparkContext.stop();
    }


    static class Student implements Serializable{

        private static final long serialVersionUID = -1L;

        private String firstName;

        private String lastName;

        private int sex;

        public Student() {

        }

        public Student(String firstName, String lastName, int sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.sex = sex;
        }

        @Override
        public int hashCode() {
            return firstName.hashCode() & lastName.hashCode() & sex;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Student) {
                Student cmp = (Student)obj;
                return firstName.equals(cmp.getFirstName())  &&
                        lastName.equals(cmp.getLastName())  &&
                        sex == cmp.getSex() ;
            }
            return false;

        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }

}
