package com.clusterfoundry.assignment.spark_assignment.join;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment1 {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset=spark.sql("select count(e.globaleventid) as Number_of_events, sum(d.dt_2017) as Population from csv.gdelt2 e inner join csv.demography d on (e.actiongeo_countrycode=d.country_code)");
		dataset.show();
		dataset.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_21");
	}
}
