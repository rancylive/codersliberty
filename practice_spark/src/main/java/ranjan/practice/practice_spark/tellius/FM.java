package ranjan.practice.practice_spark.tellius;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;

public class FM implements FlatMapFunction<Iterator<Row>, Out>{

	@Override
	public Iterator<Out> call(Iterator<Row> t) throws Exception {
		int max=Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		Out out = new Out();
		while (t.hasNext()) {
			int num = Integer.parseInt(t.next().getString(0));
			if(num>max) {
				max=num;
			}
			if(num<min) {
				min = num;
			}
			out.setMax(max);
			out.setMin(min);
		}
		
		return Arrays.asList(out).iterator();
	}

}
