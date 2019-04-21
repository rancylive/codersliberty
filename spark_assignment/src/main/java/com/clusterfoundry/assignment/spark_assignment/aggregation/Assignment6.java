package com.clusterfoundry.assignment.spark_assignment.aggregation;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment6 {
	public static void main(String[] args) {
		System.out.println(
				"Enter space seperated arguments in given sequence - monthYear actor1geo_countrycode eventcode quadclass");
		SparkSession session = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset input = session.sql("SELECT * FROM csv.gdelt2");
		if (args.length < 4) {
			throw new IllegalArgumentException(
					"Number of arguments are less than expected. Enter space seperated arguments in given sequence - monthYear actor1geo_countrycode eventcode quadclass");
		}
		Dataset monthyearSet = input.groupBy("monthyear").count().filter("monthyear=" + args[0]);
		Dataset actor1geo_countrycodeSet = input.groupBy("actor1geo_countrycode").count().filter("actor1geo_countrycode='" + args[1]+"'");
		Dataset eventcodeSet = input.groupBy("eventcode").count().filter("eventcode='" + args[2]+"'");
		Dataset quadclassSet = input.groupBy("quadclass").count().filter("quadclass=" + args[3]);
		Dataset outputSet = monthyearSet.union(actor1geo_countrycodeSet).union(eventcodeSet).union(quadclassSet);
		outputSet.show();
		outputSet.write().format("csv").mode(SaveMode.Overwrite).saveAsTable("csv.gdelt2_out_6");
		System.out.println("Output written to csv.gdelt2_out_6");
	}
}
