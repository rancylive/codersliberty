package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment12 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.table("csv.gdelt2");
		Dataset result=dataset.filter("numarticles>100 and AvgTone>10 and AvgTone<25");
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_412");
		System.out.println("Output table is csv.gdelt2_out_412");
	}
}
