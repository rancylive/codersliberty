package com.ranjan.MapreduceTask.task3;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		JobConf conf = new JobConf();
		Job job = Job.getInstance(conf);
		job.setJarByClass(Driver.class);
		job.setJobName("SessionAnalyzer");
		job.setMapperClass(ActionMapper.class);
		job.setReducerClass(ActionReducer.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Report.class);
        
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
