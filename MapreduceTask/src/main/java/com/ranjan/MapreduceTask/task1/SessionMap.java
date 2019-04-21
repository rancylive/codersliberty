package com.ranjan.MapreduceTask.task1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionMap extends Mapper<Object, Text, IntWritable, IntWritable> {
	public static Logger log = LoggerFactory.getLogger(SessionMap.class);

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		JSONArray jsonArray = new JSONArray(value.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			int sessionId = object.getInt("sessionid");
			int appnameenc = object.getInt("appnameenc");
			int sessionDuration = object.getInt("eventlaenc");
			if (appnameenc == 1 || appnameenc == 2) {
				context.write(new IntWritable(sessionId), new IntWritable(sessionDuration));
			}
		}

	}
}
