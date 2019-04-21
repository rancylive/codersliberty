package com.clusterfoundry.assignment.spark_assignment.data_quality;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;

public class Assignment6 {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = spark.table("csv.gdelt2");
		Dataset eventByActor2ethniccode = input.groupBy("actor2ethniccode")
				.agg(functions.count("eventcode").alias("numer_of_events"));
		Dataset joined = input.select("actor2ethniccode", "monthyear").join(eventByActor2ethniccode, "monthyear");
		WindowSpec winSpec = Window.orderBy("monthyear");
		Dataset result = joined.withColumn("previous_count", functions.lag("numer_of_events", 1, 0).over(winSpec));
		String[] fields = { "actor2ethniccode" };
		Dataset out = result.na().fill("previous_count", fields);
		out.show();
		out.write().mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_d6");
	}

}
