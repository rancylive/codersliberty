package practce.ranjan.practice_kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practce.ranjan.practice_kafka.producer.CustomProducer;

@Service
public class KafkaService {
	@Autowired
	private CustomProducer producer;

	public String createTopic(String topicName) {
		return producer.createTopic(topicName);
	}

	public String produceMessage(String topic, String message) {
		return producer.produce(topic, message);
	}
}
