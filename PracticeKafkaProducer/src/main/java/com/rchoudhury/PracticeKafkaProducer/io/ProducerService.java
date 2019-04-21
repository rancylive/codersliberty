package com.rchoudhury.PracticeKafkaProducer.io;

public class ProducerService {

	public static void main(String[] args) {
		Producer producer = new Producer();
		String message = "There is something here";
		producer.send(message, "rough", 5);
	}
}
