package com.spark;

import com.google.common.collect.Lists;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hzzhaolong on 19/7/.
 *
 * api Ref:https://spark.apache.org/docs/2.3.0/api/java/index.html?org/apache/spark/sql/Dataset.html
 */
public class DataSetTest {


    public static void main(String[] args) throws IOException {

        SparkSession sparkSession = SparkSession
                .builder()
                .appName("DataSet App")
                .master("local")
                .getOrCreate();

        // DataFrame相当于DataSet[Row]
        // a. 没有编译阶段的类型检查： 不能在编译时刻对安全性做出检查，而且限制了用户对于未知结构的数据进行操作
        // b. 不能保留类对象的结构： 一旦把一个类结构的对象转成了Dataframe，就不能转回去了

        // 1. 从数组创建dataSet
        List<Student> students = Lists.newArrayList(
                new Student(1L, "zhao", "xiaohua", 0), new Student(2L, "wang", "dama", 0), new Student(3L, "zhao", "2", 1),
                new Student(4L, "wang", "wangwang", 0), new Student(5L, "li", "ming", 1), new Student(6L, "zhao", "2", 1)
        );
        Encoder<Student> studentEncoder = Encoders.bean(Student.class);
        Dataset<Student> studentDataset = sparkSession.createDataset(students, studentEncoder);

        List<StuSchool> stuSchools = Lists.newArrayList(
                new StuSchool(1L, "beida"), new StuSchool(2L, "qinghua"), new StuSchool(3L, "nanjing"),
                new StuSchool(4L, "wuhan"), new StuSchool(5L, "shenzhen"), new StuSchool(6L, "hongkong")
        );
        Encoder<StuSchool> schoolEncoder = Encoders.bean(StuSchool.class);
        Dataset<StuSchool> stuSchoolDataSet = sparkSession.createDataset(stuSchools, schoolEncoder);

        // 2. 从Rdd创建dataSet
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession.sparkContext());
        SQLContext sqlContext = new SQLContext(sparkSession);
        JavaRDD<Student> studentRdd = javaSparkContext.parallelize(students);
        Dataset<Student> datasetFromList = sqlContext.createDataset(students, studentEncoder);
        System.out.println("sqlContext============>");
        datasetFromList.show();
        Dataset<Student> datasetFromRDD = sqlContext.createDataset(studentRdd.rdd(), studentEncoder);
        datasetFromRDD.show();
        System.out.println("<============sqlContext");


        System.out.println("============>");
        studentDataset.show();
        stuSchoolDataSet.show();
        System.out.println("<============");

        // Method col
        Column firstName = studentDataset.col("firstName");

        // Method filter
        System.out.println("============>");
        Dataset<Student> filteredStudentSet = studentDataset.filter(studentDataset.col("firstName").equalTo("zhao"));
        filteredStudentSet.show();
        System.out.println("<============");

        // Method join
        System.out.println("============>");
        Dataset<Row> joinSet = studentDataset.join(stuSchoolDataSet, studentDataset.col("id").equalTo(stuSchoolDataSet.col("id")));
        joinSet.show();
        System.out.println("<============");

        // Method groupBy
        System.out.println("============>");
        studentDataset
                .groupBy(studentDataset.col("firstName"))
                .max("id").show();

        System.out.println("<============");

        // Method sort order by
        Dataset<Student> sortedDataSet = studentDataset.sort(studentDataset.col("firstName").desc());
        Dataset<Student> sortedDataSet2 = studentDataset.orderBy(studentDataset.col("firstName").desc());
        System.out.println("============>");
        sortedDataSet.show();
        sortedDataSet2.show();
        System.out.println("<============");

        // Method select
        System.out.println("============>");
        studentDataset.select("id", "firstName").show();
        System.out.println("<============");

        // Method selectExpr
        System.out.println("============>");
        studentDataset.selectExpr("id as id_new", "sex + 1").show();
        System.out.println("<============");

        // Method javaRDD
        JavaRDD<Student> studentJavaRDD = studentDataset.javaRDD();
        System.out.println("============>");
        System.out.println(studentJavaRDD.toString());
        System.out.println("<============");

        sparkSession.stop();
    }


    public static class Student implements Serializable{

        private static final long serialVersionUID = -1L;

        private Long id;

        private String firstName;

        private String lastName;

        private Integer sex;

        public Student() {

        }

        public Student(Long id, String firstName, String lastName, Integer sex) {
            this.id = id;
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
                        sex.equals(cmp.getSex()) ;
            }
            return false;

        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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


        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }
    }

    public static class StuSchool implements Serializable{

        private static final long serialVersionUID = -1L;

        private Long id;

        private String schoolName;

        public StuSchool() {

        }

        public StuSchool(Long id, String schoolName) {
            this.id = id;
            this.schoolName = schoolName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }

}
