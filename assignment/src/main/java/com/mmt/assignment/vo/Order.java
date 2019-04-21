package com.mmt.assignment.vo;

public class Order {
	private int orderId;
	private int customerId;
	private int orderAmount;

	public Order() {}
	
	public Order(int orderId, int customerId, int orderAmount) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderAmount = orderAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	public String toString() {
		return orderId + "," + customerId + "," + orderAmount;
	}

}
