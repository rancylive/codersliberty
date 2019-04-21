package ranjan.practice.practice_spark.spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class DatasetCreate {
	public static void main(String[] args) {
		transpose(createDataFrame(), "field2");
	}
	
	public static Dataset createDataFrame() {
		SparkSession spark = SparkSession.builder().master("local").getOrCreate();
		StructType structType = DataTypes.createStructType(new StructField[] {
				DataTypes.createStructField("field1", DataTypes.StringType, true),
				DataTypes.createStructField("field2", DataTypes.StringType, true),
	            DataTypes.createStructField("field3", DataTypes.StringType, true),
	            DataTypes.createStructField("field4", DataTypes.StringType, true),
	            DataTypes.createStructField("field5", DataTypes.StringType, true)
		});
		List<Row> data=new ArrayList();
		data.add(RowFactory.create("apple","v2","v3","v4","v5"));
		data.add(RowFactory.create("apple1","v22","v33","v44","v55"));
		Dataset in=spark.createDataFrame(data, structType);
		//Dataset in = spark.read().schema(structType).option("header", "true").csv("file:///Users/rchoudhury/data/emp.csv");
		in.show();
		return in;
	}
	
	public static void transpose(Dataset dataset, String column) {
		StructType schema = dataset.schema();
		Dataset<Row> trans = dataset.groupBy("field1").pivot(column).agg(functions.collect_list("field3"));
		trans.show();
		dataset.show();
		
		String[] cols = dataset.columns();
		for(String col: cols) {
			
		}
/*		val kv = explode(array(df.columns.tail.map { 
			  c => struct(lit(c).alias("k"), col(c).alias("v")) 
			}: _*))

			df
			  .withColumn("kv", kv)
			  .select($"segment_id", $"kv.k", $"kv.v")
			  .groupBy($"k")
			  .pivot("segment_id")
			  .agg(first($"v"))
			  .orderBy($"k")
			  .withColumnRenamed("k", "vals") */
		//TODO
	}
	
	public static void pivot(Dataset dataset) {
		//TODO
	}
	
}
