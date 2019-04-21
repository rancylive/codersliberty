package com.clusterfoundry.assignment.spark_assignment.join;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment2 {
	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = session.sql("select actiongeo_countrycode,monthyear, numarticles from csv.gdelt2");
		Dataset newdataset = dataset.groupBy("actiongeo_countrycode").pivot("monthyear").sum("numarticles");
		newdataset.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_22");
	}
}
