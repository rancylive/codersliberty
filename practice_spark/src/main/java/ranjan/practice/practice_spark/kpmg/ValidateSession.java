package ranjan.practice.practice_spark.kpmg;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.types.DataTypes;

import scala.collection.Seq;

public class ValidateSession {

	public static void main(String[] args) {
		SparkSession spark=SparkSession.builder().master("local").getOrCreate();
		spark.udf().register("toMinute", new UDF1<Long, String>() {
			@Override
			public String call(Long t1) throws Exception {
				int n=((Number)t1).intValue();
				return "ddb";
			}
		}, DataTypes.IntegerType);
		WindowSpec window = Window.partitionBy("_c1").orderBy("_c0");
		Dataset input=spark.read().csv("file:///Users/rchoudhury/userClick");
		Dataset<Row> in = input.sort("_c1");
		Dataset<Row> out = in.withColumn("timeDiff", in.col("_c0").minus(functions.lag("_c0", 1).over(window))).na().fill(0,new String[]{"timeDiff"});
		Dataset<Row> out1 =  out.withColumn("addedTime", out.col("timeDiff").plus(functions.lag("timeDiff", 1).over(window))).na().fill(0,new String[]{"addedTime"});
		Dataset out2 = out1.withColumn("session", functions.when(out1.col("addedTime").gt(functions.lit(30)), "s"));
		out2.show();
	}
}
