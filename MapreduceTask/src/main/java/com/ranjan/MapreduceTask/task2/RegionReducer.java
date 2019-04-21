package com.ranjan.MapreduceTask.task2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.common.collect.Iterators;

public class RegionReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		int count = Iterators.size(arg1.iterator());
		arg2.write(arg0, new IntWritable(count));
	}
}
