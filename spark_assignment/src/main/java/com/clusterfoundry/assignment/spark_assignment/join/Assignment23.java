package com.clusterfoundry.assignment.spark_assignment.join;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment23 {
	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = session.sql("select monthyear, numarticles from csv.gdelt2");
		Dataset newdataset = dataset
				.withColumn("Num_Articles_Band",
						functions
								.when(dataset.col("numarticles").geq(0).and(dataset.col("numarticles").leq(100)),
										functions.lit("1-100"))
								.otherwise(functions
										.when(dataset.col("numarticles").geq(101)
												.and(dataset.col("numarticles").leq(1000)), functions.lit("100-1000"))
										.otherwise(functions.when(
												dataset.col("numarticles").geq(1001)
														.and(dataset.col("numarticles").leq(10000)),
												functions.lit("100-10000")))));
		Dataset result = newdataset.groupBy("Num_Articles_Band").agg(functions.sum("numarticles").as("Sum_Of_numarticles"))
				.orderBy("Num_Articles_Band");
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_23");
	}
}
