package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment7 {
	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = session.table("csv.gdelt2");
		Dataset result = dataset.filter("goldsteinscale<0 and AvgTone<0");
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_47");
		System.out.println("Output table is csv.gdelt2_out_47");
	}
}
