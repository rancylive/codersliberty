package com.clusterfoundry.assignment.spark_assignment.aggregation;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Assignment4 {
	public static void main(String[] args) {
		SparkSession sparkSession = SparkSession.builder().appName("Assignment-4").enableHiveSupport().getOrCreate();
		Dataset<EventsChangeReport> result = sparkSession
				.sql("SELECT `monthyear` as monthYear, COUNT(globaleventid) as numberOfEvents, 0 as changePercentage from `csv`.`gdelt2` GROUP BY `monthyear`")
				.as(Encoders.bean(EventsChangeReport.class));
		List<EventsChangeReport> records = result.collectAsList();
		double previousEventsCount = 0;
		/*records.forEach(record-> {
			long currentEventsCount = record.getNumberOfEvents();
			double diff = 0;
			if(previousEventsCount !=0 ) {
				diff = ((previousEventsCount - currentEventsCount)*100)/previousEventsCount;
			}
			previousEventsCount = currentEventsCount;
			record.setChangePercentage(diff);
		});*/
		for(EventsChangeReport record:records) {
			long currentEventsCount = record.getNumberOfEvents();
			double diff = 0;
			if(previousEventsCount !=0 ) {
				diff = ((previousEventsCount - currentEventsCount)*100)/previousEventsCount;
			}
			previousEventsCount = currentEventsCount;
			record.setChangePercentage(diff);
		}
		Dataset outputSet = sparkSession.createDataset(records, Encoders.bean(EventsChangeReport.class));
		outputSet.show();
		outputSet.write().mode(SaveMode.Overwrite).saveAsTable("csv.GDELT_out_4");
		System.out.println("Report has been saved to table csv.GDELT_out_4");
	}
}
