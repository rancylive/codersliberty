package com.clusterfoundry.assignment.spark_assignment.aggregation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class Assignment5 {
	public static void main(String[] args) {

		String monthYr = "201707";
		String maxCount = "100";
		if (args != null && args.length > 1) {
			monthYr = args[0];
			maxCount = args[1];
		}
		SparkSession sparkSession = SparkSession.builder().appName("Assignment-5").enableHiveSupport().getOrCreate();
		Dataset input = sparkSession.sql("SELECT * FROM `csv`.`gdelt2`");
		Dataset num_Events = input.groupBy("monthyear", "actiongeo_countrycode").count();
		num_Events.show();
		Dataset countryCount = num_Events.filter("count>" + maxCount).groupBy("monthyear").count()
				.filter("monthyear=" + monthYr).select("count");
		countryCount.show();
	}
}
