package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment9 {
	public static void main(String[] args) {
		if(args.length<1) {
			args = new String[1];
			args[0]="dateadded";
		}
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=session.table("csv.gdelt2");
		Dataset result=dataset.na().fill(-99,args);
		result.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_49");
		System.out.println("Null replced by -99 for fields "+args.toString()+" in table csv.gdelt2_out_49");
	}
}
