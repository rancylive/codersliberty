package com.ranjan.MapreduceTask.task3;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

public class Report implements Writable {
	private IntWritable calc_userid;
	private IntWritable first_action;
	private IntWritable first_action_count;
	private IntWritable second_action;
	private IntWritable second_action_count;

	public Report(IntWritable calc_userid, IntWritable first_action, IntWritable first_action_count,
			IntWritable second_action, IntWritable second_action_count) {
		this.calc_userid = calc_userid;
		this.first_action = first_action;
		this.first_action_count = first_action_count;
		this.second_action = second_action;
		this.second_action_count = second_action_count;
	}

	public void write(DataOutput out) throws IOException {
		calc_userid.write(out);
		first_action.write(out);
		first_action_count.write(out);
		second_action.write(out);
		second_action_count.write(out);
	}

	public void readFields(DataInput in) throws IOException {
		calc_userid.readFields(in);
		first_action.readFields(in);
		first_action_count.readFields(in);
		second_action.readFields(in);
		second_action_count.readFields(in);
	}

	@Override
	public String toString() {
		return "Report [calc_userid=" + calc_userid + ", first_action=" + first_action + ", first_action_count="
				+ first_action_count + ", second_action=" + second_action + ", second_action_count="
				+ second_action_count + "]";
	}

}
