package com.spark;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

/**
 * Created by hzzhaolong on 19/7/5.
 */
public class SQLContextTest {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("DataSet App")
                .master("local")
                .getOrCreate();

        SQLContext sqlContext = new SQLContext(sparkSession);

//        sqlContext.sql()
    }
}
