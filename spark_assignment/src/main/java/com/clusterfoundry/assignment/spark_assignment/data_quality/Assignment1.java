package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment1 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.sql("select * from csv.gdelt2");
		dataset.dropDuplicates("sourceurl").write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_41");
		System.out.println("Output records are written to csv.gdelt2_out_41");
	}
}
