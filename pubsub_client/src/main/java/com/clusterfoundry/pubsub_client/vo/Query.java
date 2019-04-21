package com.clusterfoundry.pubsub_client.vo;

public class Query {
	private String first;
	private String second;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Query [first=" + first + ", second=" + second + "]";
	}

}
