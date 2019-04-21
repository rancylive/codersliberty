package ranjan.practice.practice_spark.tellius;

import java.io.Serializable;

public class Out implements Serializable {

	int max;
	int min;
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Out [max=" + max + ", min=" + min + "]";
	}
	
}
