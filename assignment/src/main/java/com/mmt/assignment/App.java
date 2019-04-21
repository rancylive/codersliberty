package com.mmt.assignment;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	Join joiner = new Join();
        joiner.join("/Users/rchoudhury/data/mmt/orders.csv", "/Users/rchoudhury/data/mmt/customers.csv", "/Users/rchoudhury/data/mmt/CustomerOrder.csv");
    }
}
