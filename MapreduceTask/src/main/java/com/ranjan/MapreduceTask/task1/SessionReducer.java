package com.ranjan.MapreduceTask.task1;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionReducer extends Reducer<IntWritable, IntWritable, IntWritable, DoubleWritable> {

	public static Logger log = LoggerFactory.getLogger(SessionReducer.class);

	@Override
	protected void reduce(IntWritable arg0, Iterable<IntWritable> arg1,
			Reducer<IntWritable, IntWritable, IntWritable, DoubleWritable>.Context arg2)
			throws IOException, InterruptedException {
		int sum = 0;
		int count = 0;
		Iterator<IntWritable> values = arg1.iterator();
		while (values.hasNext()) {
			sum += values.next().get();
			count++;
		}
		log.info("Count: " + count);
		double avg = sum / count;
		arg2.write(arg0, new DoubleWritable(avg));
	}

}
