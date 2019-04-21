package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;

public class Assignment11 {
	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = session.table("csv.gdelt2").withColumn("numarticles",
				functions.col("numarticles").cast(DataTypes.IntegerType));
		dataset.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_411");
		dataset.printSchema();
		System.out.println("Output table is csv.gdelt2_out_411");
	}
}
