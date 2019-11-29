package com.xeuj.analytics;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
//import org.apache.spark.mllib.rdd.*;

public class LogAnalyser {
    public static void main(String[] args) {
        System.out.println("Spark log Analyser");
        String inputFile = args[0] == null?"":args[0];
        SparkSession spark = SparkSession.builder().appName("LogAnalyser").master("local").getOrCreate();
        JavaRDD input = spark.sparkContext().textFile(inputFile, 10).toJavaRDD();
        input.map(line->line);

    }
}
