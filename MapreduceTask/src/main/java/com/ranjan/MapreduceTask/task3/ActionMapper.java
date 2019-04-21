package com.ranjan.MapreduceTask.task3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class ActionMapper extends Mapper<Object, Text, IntWritable, Text>{
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		JSONArray jsonArray = new JSONArray(value.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			int eventlaenc = object.getInt("eventlaenc");
			int calc_userid = object.getInt("calc_userid");
			if(eventlaenc == 126) {
				context.write(new IntWritable(calc_userid), new Text("first_action"));
			}
			if(eventlaenc == 107) {
				context.write(new IntWritable(calc_userid), new Text("second_action"));
			}
		}

	}
}
