package com.ranjan.MapreduceTask.task2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class RegionMapper extends Mapper<Object, Text, Text, IntWritable>{
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		JSONArray jsonArray = new JSONArray(value.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String region = object.getString("region");
			int calc_userid = object.getInt("calc_userid");
			if(!(region.equals("null") || region.equals("-"))) {
				context.write(new Text(region), new IntWritable(calc_userid));
			}
		}
	}
}
