package com.clusterfoundry.assignment.spark_assignment.debugging;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Assignment1 {
	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset ds=session.sql("select numarticles from csv.gdelt2");
		/*int max = (Integer) ds.agg(functions.max("numarticles")).collect();
		System.out.println("Max of numarticles is: "+max);
		int min = (Integer) ds.agg(functions.min("numarticles")).collect();
		System.out.println("Min of numarticles is: "+min);
		int avg = (Integer) ds.agg(functions.avg("numarticles")).collect();
		System.out.println("Average of numarticles is: "+avg);
		int mean = (Integer) ds.agg(functions.mean("numarticles")).collect();
		System.out.println("Mean of numarticles is: "+mean);*/
		ds.describe().show();
	}
}
