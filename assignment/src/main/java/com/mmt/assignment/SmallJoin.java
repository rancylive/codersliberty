package com.mmt.assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mmt.assignment.vo.CustomerOrder;

public class SmallJoin {
	public void join(String left, String right, String output) throws IOException {
		BufferedReader leftReader = null;
		BufferedReader rightReader = null;
		FileWriter outWriter = null;
		try {
			leftReader = new BufferedReader(new FileReader(left));
			rightReader = new BufferedReader(new FileReader(right));
			outWriter = new FileWriter(output);
			Map<Integer, String> customer = new HashMap<Integer, String>();
			String line = rightReader.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				if(fields.length<2)
					return;
				customer.put(Integer.parseInt(fields[0]), fields[1]);
				line = rightReader.readLine();
			}
			line = leftReader.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				if(fields.length<3)
					return;
				int customerId = Integer.parseInt(fields[1]);
				String customerEmail = customer.get(customerId);
				CustomerOrder joinedData = new CustomerOrder();
				joinedData.setOrderId(Integer.parseInt(fields[0]));
				joinedData.setCustomerId(customerId);
				joinedData.setOrderAmount(Integer.parseInt(fields[2]));
				joinedData.setCustomerEmail(customerEmail);
				outWriter.write(joinedData.toString().concat("\n"));
				line = leftReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (leftReader != null)
				leftReader.close();
			if (rightReader != null)
				rightReader.close();
			if (outWriter != null)
				outWriter.close();
		}
	}
}
