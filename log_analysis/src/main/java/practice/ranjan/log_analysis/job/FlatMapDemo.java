package practice.ranjan.log_analysis.job;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class FlatMapDemo {
	public static void main1(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		Dataset<String> input = spark.read().textFile("/Users/ranjanchoudhury/eclipse-workspace/logFile.json");
		Dataset<String> fmap = input.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public Iterator call(String t) throws Exception {
				String tt = t.replaceAll("\\}\\{", "\\}\n\\{");
				List<String> rows = Arrays.asList(tt.split("\n"));
				return rows.iterator();
			}
		}, Encoders.STRING());
		fmap.show();
	}

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		Dataset<Row> input = spark.read().json("/Users/ranjanchoudhury/eclipse-workspace/logFile.json");
		input.select("data.events").write().json("/Users/ranjanchoudhury/eclipse-workspace/logFile_out.json");
	}
}
