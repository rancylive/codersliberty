package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class Assignment5 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = session.table("csv.gdelt2");
		input.printSchema();
	}
}
