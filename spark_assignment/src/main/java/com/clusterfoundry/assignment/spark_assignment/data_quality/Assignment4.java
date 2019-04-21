package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment4 {
	public static void main(String[] args) {
		if (args.length < 1) {
			args = new String[1];
			args[0] = "actor1ethniccode";
		}
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = session.sql("select * from csv.gdelt2");
		Dataset output = input.na().drop("all", args);
		output.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_44");
		System.out.println(output.count() + " number of records are written to csv.gdelt2_out_44");
	}	
}
