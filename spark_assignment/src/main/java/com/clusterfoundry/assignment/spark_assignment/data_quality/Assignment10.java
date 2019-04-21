package com.clusterfoundry.assignment.spark_assignment.data_quality;

import java.util.Arrays;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment10 {
	public static void main(String[] args) {
		if(args.length<1) {
			throw new IllegalArgumentException("Enter atleast one field name");
		}
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.table("csv.gdelt2");
		Dataset<Row> result=dataset.select(args[0], Arrays.copyOfRange(args, 1, args.length));
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_410");
		System.out.println("Output table is csv.gdelt2_out_410");
	}
}
