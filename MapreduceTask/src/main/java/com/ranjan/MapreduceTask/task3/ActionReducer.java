package com.ranjan.MapreduceTask.task3;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ActionReducer extends Reducer<IntWritable, Text, IntWritable, Report> {
	@Override
	protected void reduce(IntWritable arg0, Iterable<Text> arg1,
			Reducer<IntWritable, Text, IntWritable, Report>.Context arg2) throws IOException, InterruptedException {
		Iterator<Text> itr = arg1.iterator();
		int firstActionCount = 0;
		int secondActionCount = 0;
		while (itr.hasNext()) {
			String action_type = itr.next().toString();
			if (action_type.equals("first_action")) {
				firstActionCount++;
			} else if (action_type.equals("second_action")) {
				secondActionCount++;
			}
		}
		Report report = new Report(arg0, new IntWritable(126), new IntWritable(firstActionCount), new IntWritable(107),
				new IntWritable(secondActionCount));
		arg2.write(arg0, report);
	}
}
