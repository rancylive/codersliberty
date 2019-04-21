package ranjan.practice.practice_spark.tellius;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class ReadCSV {

	public static void main(String[] args) {
		SparkSession session=SparkSession.builder().master("local").appName("Reader").getOrCreate();
		Dataset<Row> input = session.read().csv("/Users/rchoudhury/data/tellius/tellius_dataset");
		JavaRDD<Employee> parsed = input.toJavaRDD().map(r->new Employee(Integer.parseInt(r.getString(0)), r.getString(1), r.getString(1), r.getString(1)));
		
		Dataset<Row> out = session.createDataFrame(parsed, Employee.class);
		out.show();
		out.printSchema();
	}
	
	
	
	void fun() {
		//input.toDF("ID","Name","Dept","Location").show();
				//input.foreach(r->new Tuple4(Integer.parseInt(r.getString(0)), r.getString(1), r.getString(1), r.getString(1)));
				List<StructField> fields = new ArrayList<>();
				fields.add(DataTypes.createStructField("ID", DataTypes.IntegerType, true));
				fields.add(DataTypes.createStructField("Name", DataTypes.StringType, true));
				fields.add(DataTypes.createStructField("Dept", DataTypes.StringType, true));
				fields.add(DataTypes.createStructField("Location", DataTypes.StringType, true));
				StructType schema = DataTypes.createStructType(fields);
	}
}
