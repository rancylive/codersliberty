package com.clusterfoundry.assignment.spark_assignment.debugging;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment2 {
	public static void main(String[] args) {
		String filePath = "hdfs://cluster-a221-m/user/ranjan/csv/gdelt2_csv/20150218230000.export.CSV";
		if (args.length > 0) {
			filePath = args[0];
		}
		SparkSession session = SparkSession.builder().getOrCreate();
		Dataset dataset = session.read().option("delimiter","\t").csv(filePath);
		Object fline = dataset.first();
		System.out.println("First record: " + fline);
		Object lastline = dataset.orderBy(functions.desc("_c0")).first();
		System.out.println("Last record: " + lastline);
	}
}
