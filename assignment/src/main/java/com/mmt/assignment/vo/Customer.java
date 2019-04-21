package com.mmt.assignment.vo;

public class Customer {
	private int customerId;
	private String customerEmail;

	public Customer(int customerId, String customerEmail) {
		super();
		this.customerId = customerId;
		this.customerEmail = customerEmail;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Override
	public String toString() {
		return customerId + ","+ customerEmail;
	}

}
