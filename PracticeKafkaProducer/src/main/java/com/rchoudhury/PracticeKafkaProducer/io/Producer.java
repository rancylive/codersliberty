package com.rchoudhury.PracticeKafkaProducer.io;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

	public static KafkaProducer producer = null;

	public void send(String message, String topic, int key) {
		if (producer == null) {
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			producer = new KafkaProducer(properties);
		}
		System.out.println("Publishing message ...");
		Future future = sendAsyncCallback(message, topic, key);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		if (future.isDone()) {
			System.out.println("Message published into topic " + topic);
		} else {
			System.out.println("Message not published into topic " + topic);
		}
	}

	public Future sendAsync(String message, String topic) {
		return producer.send(new ProducerRecord(topic, message));
	}

	public Future sendAsyncCallback(String message, String topic, int key) {
		Long long1=new Long(key);
		ProducerRecord record = new ProducerRecord(topic, key, message);
		return producer.send(record, new Callback() {
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception != null) {
					exception.printStackTrace();
				} else {
					System.out.println("Message published with offset " + metadata.offset() + " partition: "
							+ metadata.partition());
				}
			}
		});
	}
}
