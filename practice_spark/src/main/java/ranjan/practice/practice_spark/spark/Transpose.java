package ranjan.practice.practice_spark.spark;

import org.apache.calcite.avatica.ColumnMetaData.StructType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import scala.collection.Seq;

public class Transpose {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		Dataset<Row> input = spark.read().option("header", "true").csv("file:///Users/rchoudhury/data/COLUMN_VALUE.csv");
		input.show();
		String transposeColumn = "Column";
		//Seq<String> sq = new Seq<String>();
		//new_schema = input.select(functions.collect_list(transposeColumn)).first().getAs(Seq<String>)(0);
	}
}
