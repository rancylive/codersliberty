package com.clusterfoundry.assignment.spark_assignment.aggregation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment7 {
	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = session.sql("SELECT * FROM csv.gdelt2");
		session.sql("select monthyear,mean(numarticles) as mean_numarticles from csv.gdelt2 group by monthyear").write()
				.mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_7");
	}
}
