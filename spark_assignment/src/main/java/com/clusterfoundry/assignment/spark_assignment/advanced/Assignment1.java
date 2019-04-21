package com.clusterfoundry.assignment.spark_assignment.advanced;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class Assignment1 {
	public static void main(String[] args) {
		SparkSession sparkSession=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=sparkSession.table("csv.gdelt2");
		dataset.persist();
	}
}
