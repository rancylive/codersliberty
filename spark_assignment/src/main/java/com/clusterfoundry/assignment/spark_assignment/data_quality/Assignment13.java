package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment13 {
	public static void main(String[] args) {
		String[] query = { "modi", "trump" };
		if (args.length > 1) {
			query[0] = args[0];
			query[1] = args[1];
		}
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = session.table("csv.gdelt2");
		Dataset result = dataset.filter(
				functions.col("actor1name").contains(query[0]).or(functions.col("actor1name").contains(query[1])));
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_413");
		System.out.println("Output table is csv.gdelt2_out_413");
	}
}
