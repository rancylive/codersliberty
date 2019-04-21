package com.clusterfoundry.pubsub_client.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.clusterfoundry.pubsub_client.publisher.CustomPubsubPublisher;
import com.clusterfoundry.pubsub_client.subscriber.CustomSubscriber;
import com.clusterfoundry.pubsub_client.vo.Query;

@Service
public class QueryService {

	//@Value("${google.cloud.pubsub.topic}")
	private static String topic_id;

	@Autowired
	private Environment environment;
	
	@Autowired
	private CustomPubsubPublisher publisher;

	@Autowired
	private CustomSubscriber subscriber;
	
	public void publishQuery(Query query) throws Exception {
		topic_id = environment.getProperty("google.cloud.pubsub.topic");
		List<String> messages = Arrays.asList(query.getFirst(), query.getSecond());
		publisher.publish(messages, topic_id);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void subscribeQuery() {
		topic_id = environment.getProperty("google.cloud.pubsub.topic");
		subscriber.subscribe(topic_id);
	}
}
