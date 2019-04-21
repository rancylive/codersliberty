package com.clusterfoundry.assignment.spark_assignment.aggregation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment9 {
	
	public static void main(String[] args) {
		String date = "20140218";
		if (args!=null && args.length > 0) {
			date = args[0];
		}
		SparkSession spark = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = spark.table("csv.gdelt2");
		Dataset eventsPerDay = input.groupBy("sqldate").agg(functions.collect_list("globaleventid").alias("Events"), functions.count("globaleventid").alias("NumberOfEvents"));
		eventsPerDay.show();
		eventsPerDay.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_9");
	}
}
