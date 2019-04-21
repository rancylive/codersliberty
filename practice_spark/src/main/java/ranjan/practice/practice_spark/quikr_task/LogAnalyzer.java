package ranjan.practice.practice_spark.quikr_task;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class LogAnalyzer {
	public static void main(String[] args) {
		SparkSession spark=SparkSession.builder().master("local").getOrCreate();
		Dataset input=spark.read().textFile("file:///Users/rchoudhury/data/");
		input.show();
		input.write().format("csv").csv("/user/rchoudhury/data/output2");
	}
}
