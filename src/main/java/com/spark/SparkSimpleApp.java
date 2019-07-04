package com.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.io.File;
import java.io.IOException;

/**
 * Created by hzzhaolong on 19/7/3.
 */
public class SparkSimpleApp {



    public static void main(String[] args) throws IOException {
        SparkConf sparkConf= new SparkConf()
                .setAppName("Simple Application")
                .setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        String logPath = new File("..").getCanonicalPath() + "/my-common-util/src/main/resources/spark-testdata1.txt";
        JavaRDD<String> logData = sparkContext.textFile(logPath).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("a");
            }
        }).count();


        long numBs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("b");
            }
        }).count();

        System.out.print("==============>");
        System.out.print("Lines with a: " + numAs + ", lines with b: " + numBs);
        System.out.println("<==============");

        sparkContext.stop();
    }
}
