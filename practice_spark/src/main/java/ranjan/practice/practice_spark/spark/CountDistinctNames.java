package ranjan.practice.practice_spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Row;

import scala.Tuple2;

public class CountDistinctNames {
	public void countDistinctNamesUsingRDD(String fileName) {
		SparkConf config = new SparkConf();
		config.set("spark.master", "local[*]");
		config.set("deploy-mode", "client");
		config.set("spark.app.name", "CountDistinctNames");
		SparkContext sc = SparkContext.getOrCreate(config);
		// number of partitions equal to the number of cores in the cluster so that all
		// the partitions will process in parallel and the resources will be utilized in
		// an optimal way. The local machine has 8 cores, so partitions=8 but since the
		// input file is very small partition=1 to reduce scheduling overhead
		RDD<String> inputRDD = sc.textFile("/user/rchoudhury/csv/data.csv", 1);
		JavaPairRDD<String, Row> temp = inputRDD.toJavaRDD().mapToPair((PairFunction<String, String, Row>) rows -> {
			String key = rows.split(",")[0];
			return new Tuple2(key, rows);
		});
		temp.groupByKey().foreach(v -> {
			System.out.println(v);
		});
	}

	public static void main(String[] args) throws InterruptedException {
		new CountDistinctNames().countDistinctNamesUsingRDD("/user/rchoudhury/csv/data.csv");
		Thread.sleep(30000);
	}
}
