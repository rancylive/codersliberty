package com.clusterfoundry.assignment.spark_assignment.advanced;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;

public class Assignment2 {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset dataset = spark.table("csv.gdelt2");
		Dataset eventCountByDay = dataset.groupBy("sqldate").agg(functions.count("eventcode").alias("count"));
		WindowSpec windowSpec = Window.orderBy("sqldate");
		Dataset nds = eventCountByDay.withColumn("previous_count",functions.lag("count", 1, 0).over(windowSpec));
		nds.show();
		String[] fields = {"count"};
		Dataset emptyReplaced = nds.na().fill("previous_count",fields).drop("previous_count");
		emptyReplaced.show();
	}
}
