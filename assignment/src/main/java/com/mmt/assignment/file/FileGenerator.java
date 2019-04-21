package com.mmt.assignment.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.mmt.assignment.vo.Customer;
import com.mmt.assignment.vo.Order;

public class FileGenerator {

	public static void main(String[] args) throws IOException {
		generateOrders();
		generateCustomers();
	}
	
	public static void generateOrders() throws IOException {
		FileWriter fw=new FileWriter("/Users/rchoudhury/data/mmt1/orders.csv", true);
		for(int i=0;i<999;i++) {
			Random random=new Random();
			fw.write(new Order(random.nextInt(100000), random.nextInt(100000), random.nextInt(100000)).toString().concat("\n"));
		}
	}
	
	public static void generateCustomers() throws IOException {
		FileWriter fw=new FileWriter("/Users/rchoudhury/data/mmt1/customers.csv", true);
		for(int i=0;i<999;i++) {
			fw.write(new Customer(i, "user"+i+"@gmail.com").toString().concat("\n"));
		}
	}
}
