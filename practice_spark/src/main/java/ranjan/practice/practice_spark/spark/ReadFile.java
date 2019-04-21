package ranjan.practice.practice_spark.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class ReadFile {

	public static void main(String[] args) {
		SparkSession spark= SparkSession.builder().appName("ReadFile").enableHiveSupport().getOrCreate();
		Dataset input=spark.read().option("delimiter", ",").csv(args[0]);
		input.show();
		input.write().saveAsTable("ran.out1");
		input.write().insertInto("ran.out1");
		System.out.println("saved to ran.out and inserted into ran1.out1");
	}
}
