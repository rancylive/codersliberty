package ranjan.practice.practice_spark.tellius;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class task2 {

	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().master("local").appName("Reader").getOrCreate();
		Dataset input = session.read().csv("/Users/rchoudhury/data/tellius/tellius2").repartition(2);
		JavaRDD<Out> out = input.toJavaRDD().mapPartitions(new FM());
		Out o = out.reduce(new Function2<Out, Out, Out>() {
			@Override
			public Out call(Out v1, Out v2) throws Exception {
				Out out = new Out();
				if (v1.max > v2.max) {
					out.setMax(v1.max);
				} else {
					out.setMax(v2.max);
				}
				if (v1.min < v2.min) {
					out.setMin(v1.min);
				} else {
					out.setMin(v2.min);
				}
				return out;
			}
		});
		System.out.println(o);
	}

}
