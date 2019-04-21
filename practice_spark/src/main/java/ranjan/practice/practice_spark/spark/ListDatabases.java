package ranjan.practice.practice_spark.spark;

import java.util.List;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ListDatabases {
	public static void main(String[] args) {
		if(System.getenv("env") != null && System.getenv("env").equals("local")) {
			System.setProperty("hive.metastore.sasl.enabled", "true");
			System.setProperty("hive.security.authorization.enabled", "false");
			System.setProperty("hive.metastore.execute.setugi", "true");
			System.setProperty("hive.metastore.uris", "thrift://cmhmaprdr01n02.lbidts.com:9083");
		}
		SparkSession session = SparkSession.builder().master("local").appName("ListDatabases").enableHiveSupport().getOrCreate();
		List<Row> dbs = session.sql("SHOW DATABASES").collectAsList();
		dbs.forEach(r->{
			System.out.println(r);
		});
	}
}
