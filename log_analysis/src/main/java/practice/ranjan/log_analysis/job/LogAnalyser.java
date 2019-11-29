package practice.ranjan.log_analysis.job;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class LogAnalyser {

	public static void main(String[] args) {
		String logLineStartToken = "	";
		SparkSession sparkSession = SparkSession.builder().appName("LogAnalyser").master("local").getOrCreate();
		JavaRDD<String> input = sparkSession.read().textFile("/Users/ranjanchoudhury/repo/escrowconsole/logs/c2cService/application.log").javaRDD();
		List<Integer> lineLengths = input.map(line->{
			return line.length();
		}).collect();
		System.out.println("Number of log lines: "+lineLengths.size());
		 List<Tuple2<Integer, Boolean>> errors = input.map(line -> {
			boolean isErrorLine = false;
			int key = 1;
			if(line.startsWith("	")) {
				isErrorLine = true;
				return new Tuple2<Integer, Boolean>(key, isErrorLine);
			} else {
				key = line.hashCode();
			}
			if(!isErrorLine) {
				key++;
			}
			return new Tuple2<Integer, Boolean>(key, isErrorLine);
		}).collect();
		System.out.println(errors);
		 
	}
}
