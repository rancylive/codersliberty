package ranjan.practice.practice_spark.spark;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;

import ranjan.practice.practice_spark.udf.DaysBetweenDates;

/**
 * Write a program which will read a file of the following format
 - CITY_NAME, DATE, DAILY_TEMPERATURE
 and output the following
     - CITY_NAME, AVERAGE_TEMPERATURE in LAST 30 DAYS,AVERAGE_TEMPERATURE in LAST 45 DAYS,AVERAGE_TEMPERATURE in LAST 60 DAYS
	 - DATE, AVERAGE_TEMPERATURE
	 - TOP 5 HOTTEST CITIES in LAST 30 days
	 - TOP 5 COOLEST CITIES in LAST 30 days
 * @author rchoudhury
 *
 */
public class CityTemperatureAnalyser {
	public static final String dateFormat = "dd/mm/yyyy";
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String currentDate = sdf.format(new Date(System.currentTimeMillis()));
		SparkSession spark = SparkSession.builder().appName("CityTempteratureAnalyser").master("local").getOrCreate();
		Dataset input = spark.read().option("header", "true")
				.csv("file:///Users/rchoudhury/data/CITY_DATE_TEMP.csv");
		spark.udf().register("DaysBetweenDates", new DaysBetweenDates(), DataTypes.IntegerType);
		problem1(input,currentDate);
	}
	public static void problem1(Dataset input, String currentDate) {
		// AVERAGE_TEMPERATURE in LAST 30 DAYS
				Dataset cityDaysDiff = input.withColumn("DaysDiff",
						functions.callUDF("DaysBetweenDates", input.col("DATE"), functions.lit(currentDate)));
		Dataset cityMaxTemp30 = cityDaysDiff.where(cityDaysDiff.col("DaysDiff").leq(30)).groupBy("CITY_NAME")
				.agg(functions.max("DAILY_TEMPERATURE").as("Max_Temperature"))
				.select(cityDaysDiff.col("CITY_NAME").as("CITY"), functions.col("Max_Temperature"));
/*		Dataset out1 = cityMaxTemp30
				.join(input,
						cityMaxTemp30.col("CITY").equalTo(input.col("CITY_NAME")).and(
								cityMaxTemp30.col("Max_Temperature").equalTo(input.col("DAILY_TEMPERATURE"))),
						"inner")
				.select(cityMaxTemp30.col("CITY").as("CITY_NAME"), cityMaxTemp30.col("Max_Temperature"),
						input.col("DATE"), input.col("DAILY_TEMPERATURE"));
		*/
		//AVERAGE_TEMPERATURE in LAST 45 DAYS
		Dataset cityMaxTemp45 = cityDaysDiff.where(cityDaysDiff.col("DaysDiff").leq(45)).groupBy("CITY_NAME")
				.agg(functions.max("DAILY_TEMPERATURE").as("Max_Temperature"))
				.select(cityDaysDiff.col("CITY_NAME").as("CITY"), functions.col("Max_Temperature"));
/*		Dataset out2 = cityMaxTemp45
				.join(input,
						cityMaxTemp45.col("CITY").equalTo(input.col("CITY_NAME")).and(
								cityMaxTemp45.col("Max_Temperature").equalTo(input.col("DAILY_TEMPERATURE"))),
						"inner")
				.select(cityMaxTemp45.col("CITY").as("CITY_NAME"), cityMaxTemp45.col("Max_Temperature"),
						input.col("DATE"), input.col("DAILY_TEMPERATURE"));
		*/
		cityMaxTemp30.show();
		cityMaxTemp45.show();
		
	}
	
}
