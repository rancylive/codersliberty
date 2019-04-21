package com.rchoudhury.PracticeKafkaConsumer.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer {

	KafkaConsumer consumer = null;
	
	public void consume(String topic) {
		try {
		if(consumer == null) {
			Properties properties = new Properties();
			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "t1111est1_new_new");
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
			properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
			consumer = new KafkaConsumer(properties);
		}
		consumer.subscribe(Arrays.asList(topic));
		while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	     }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
