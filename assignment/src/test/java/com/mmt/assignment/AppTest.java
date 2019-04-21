package com.mmt.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Join join = new Join();
		try {
			join.join("order.csv", "customer.csv", "joinedData.csv");
			BufferedReader br = new BufferedReader(new FileReader("joinedData.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				String fields[] = line.split(",");
				if (fields.length > 3) {
					assertTrue(fields[3].equals("user" + fields[1].trim() + "@gmail.com"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
