package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment14 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.table("csv.gdelt2");
		Dataset monthyears = dataset.select(functions.col("monthyear")).distinct().sort(functions.desc("monthyear")).limit(13);
//		Dataset avg = dataset.filter(functions.col("monthyear").isin(dataset.select(functions.col("monthyear")).distinct().sort(functions.desc("monthyear")).limit(13))).agg(functions.avg("numarticles"));
		Dataset avg = dataset.join(monthyears,"monthyear").agg(functions.avg("numarticles"));
		System.out.println("Average is: ");
		avg.show();
	}
}
