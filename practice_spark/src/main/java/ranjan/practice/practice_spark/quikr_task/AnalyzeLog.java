package ranjan.practice.practice_spark.quikr_task;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

/**
 * Hello world!
 *
 */
public class AnalyzeLog {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		JavaRDD<String> input = spark.sparkContext().textFile("file:///Users/rchoudhury/data/quikr\\ task\\ data.txt", 1).toJavaRDD()
				.filter(x -> x.contains("PreNotification status "));
		JavaRDD<Report> reportRdd = input.map(new Function<String, Report>() {
			@Override
			public Report call(String line) throws Exception {
				String data = line.split("PreNotification status ")[1];
				String[] fields = data.split(" ");
				Report report = new Report();
				for (String field : fields) {
					if (field.contains("hasDmail")) {
						String value = field.split(":")[1];
						if (!value.equals("null")) {
							report.setDemail(Boolean.parseBoolean(value));
						}
					}
					if (field.contains("notifWebEligible")) {
						String value = field.split(":")[1];
						if (!value.equals("null")) {
							report.setNotifWebEligible(Boolean.parseBoolean(value));
						}
					}
					if (field.contains("appEligible")) {
						String value = field.split(":")[1];
						if (!value.equals("null")) {
							report.setAppEligible(Boolean.parseBoolean(value));
						}
					}
					if (field.contains("WebEligible")) {
						String value = field.split(":")[1];
						if (!value.equals("null")) {
							report.setWebEligible(Boolean.parseBoolean(value));
						}
					}
				}
				return report;
			}
		});
		Dataset<Row> df = spark.createDataFrame(reportRdd, Report.class);
		//Dataset out=getClassification(df);
		Dataset out = df.withColumn("Classification", functions
				.when(functions.col("demail").equalTo(true).and(functions.col("appEligible").equalTo(true)), "App")
				.otherwise(functions
						.when(functions.col("demail").equalTo(true).and(functions.col("notifWebEligible").equalTo(true))
								.and(functions.col("appEligible").equalTo(false))
								.and(functions.col("WebEligible").equalTo(false)), "Waste")
						.otherwise(functions
								.when(functions.col("demail").equalTo(true)
										.and(functions.col("notifWebEligible").equalTo(true))
										.and(functions.col("appEligible").equalTo(false))
										.and(functions.col("WebEligible").equalTo(true)), "Web")
								.otherwise(functions
										.when(functions.col("demail").equalTo(true)
												.and(functions.col("notifWebEligible").equalTo(true))
												.and(functions.col("appEligible").equalTo(false)), "Waste")
										.otherwise(functions
												.when(functions.col("demail").equalTo(false)
														.and(functions.col("notifWebEligible").equalTo(false)), "Waste")
												.otherwise(functions
														.when(functions
																.col("demail").equalTo(false)
																.and(functions.col("notifWebEligible").equalTo(true))
																.and(functions.col("appEligible").equalTo(false))
																.and(functions.col("WebEligible").equalTo(true)), "Web")
														.otherwise(functions.when(functions.col("demail").equalTo(false)
																.and(functions.col("notifWebEligible").equalTo(true))
																.and(functions.col("appEligible").equalTo(false))
																.and(functions.col("WebEligible").equalTo(false)),
																"Waste")))))))); 
		out.show();
		out.write().format("csv").csv("/user/rchoudhury/data/output223");
	}
	
	public static Dataset getClassification(Dataset df) {
		Dataset out = df.withColumn("Classification", functions
				.when(functions.col("demail").equalTo(true).and(functions.col("appEligible").equalTo(true)), "App")
				.when(functions.col("demail").equalTo(true).and(functions.col("notifWebEligible").equalTo(true))
						.and(functions.col("appEligible").equalTo(false))
						.and(functions.col("WebEligible").equalTo(false)), "Waste")
				.when(functions.col("demail").equalTo(true).and(functions.col("notifWebEligible").equalTo(true))
						.and(functions.col("appEligible").equalTo(false))
						.and(functions.col("WebEligible").equalTo(true)), "Web")
				.when(functions.col("demail").equalTo(true).and(functions.col("notifWebEligible").equalTo(true))
						.and(functions.col("appEligible").equalTo(false)), "Waste")
				.when(functions.col("demail").equalTo(false).and(functions.col("notifWebEligible").equalTo(false)),
						"Waste")
				.when(functions.col("demail").equalTo(false).and(functions.col("notifWebEligible").equalTo(true))
						.and(functions.col("appEligible").equalTo(false))
						.and(functions.col("WebEligible").equalTo(true)), "Web")
				.when(functions.col("demail").equalTo(false).and(functions.col("notifWebEligible").equalTo(true))
						.and(functions.col("appEligible").equalTo(false))
						.and(functions.col("WebEligible").equalTo(false)), "Waste"));
		return out;
	}
}
