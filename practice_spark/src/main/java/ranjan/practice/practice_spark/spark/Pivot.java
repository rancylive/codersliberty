package ranjan.practice.practice_spark.spark;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Pivot {

	public static void main(String[] args) {
		customPivot();
	}

	public static void customPivot() {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		Dataset input = spark.read().csv("file:///Users/rchoudhury/practice");
		input.show();
		List<String> months = input.select("_c1").distinct().as(Encoders.STRING()).collectAsList();
		Dataset out = input.select("_c0").distinct();
		for (String month : months) {
			Dataset temp = input.where(input.col("_c1").equalTo(month));
			temp = temp.withColumn(month, functions.col("_c2"));
			out = out.join(temp.select("_c0", month), "_c0");
		}
		out.show();
	}

	public static void nativePivot() {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		Dataset input = spark.read().csv("file:///Users/rchoudhury/practice");
		input.show();
		Dataset out = input.groupBy("_c0").pivot("_c1").agg(functions.sum("_c2"));
		out.show();
	}
}
