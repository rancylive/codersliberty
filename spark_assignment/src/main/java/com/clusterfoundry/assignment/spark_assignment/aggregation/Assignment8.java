package com.clusterfoundry.assignment.spark_assignment.aggregation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment8 {
	public static void main(String[] args) {
		String monthyear = "201402";
		if (args.length > 0) {
			monthyear = args[0];
		}
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = session.table("csv.gdelt2");
		Dataset minArticlesGroup = input.groupBy("monthyear", "sqldate").min("numarticles")
				.filter(functions.col("monthyear").equalTo(monthyear)).select("sqldate").alias("Date");
		minArticlesGroup.show();
		minArticlesGroup.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_8");
		System.out.println("Output saved to csv.gdelt2_out_8");
	}
}
