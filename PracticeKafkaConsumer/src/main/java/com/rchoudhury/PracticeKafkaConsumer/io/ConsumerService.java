package com.rchoudhury.PracticeKafkaConsumer.io;

public class ConsumerService {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		consumer.consume("rough");
	}
}
