package ranjan.practice.practice_spark.quikr_task;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class TestHive {

	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().config("hive.metastore.uris", "thrift://localhost:9083").appName(TestHive.class.getSimpleName()).master("local").enableHiveSupport().getOrCreate();
		Dataset d1=session.sql("select * from db.emp");
		d1.show();
	}
}
