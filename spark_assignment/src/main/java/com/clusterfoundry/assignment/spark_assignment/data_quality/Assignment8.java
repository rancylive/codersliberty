package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment8 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.table("csv.gdelt2");
		Dataset result=dataset.withColumn("actor1geo_fullname",functions.initcap(functions.col("actor1geo_fullname")));
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_48");
		System.out.println("Output table is csv.gdelt2_out_48");
	}
}
