package com.clusterfoundry.pubsub_client.publisher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class CustomPubsubPublisher {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	@Autowired
	private Environment environment;

	// @Value( "${google.cloud.credential.file}" )
	private static String credentialFilePath;

	public void publish(List<String> messages, String topic_id) throws Exception {
		credentialFilePath = environment.getProperty("google.cloud.credential.file");
		System.out.println("Publishing: " + PROJECT_ID + " : " + credentialFilePath + " : " + messages + topic_id);
		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topic_id);
		Publisher publisher = null;
		GoogleCredentials credentials = GoogleCredentials
				.fromStream(new FileInputStream(credentialFilePath))
				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
		List<ApiFuture<String>> futures = new ArrayList<ApiFuture<String>>();
		try {
			publisher = Publisher.newBuilder(topicName).setCredentialsProvider(credentialsProvider).build();
			for (String message : messages) {
				ByteString data = ByteString.copyFromUtf8(message);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
				ApiFuture<String> future = publisher.publish(pubsubMessage);
				futures.add(future);
			}
		} finally {
			List<String> messageIds = ApiFutures.allAsList(futures).get();
			for (String messageId : messageIds) {
				System.out.println("Query published with message ID "+messageId);
			}
			if (publisher != null) {
				publisher.shutdown();
			}
		}
	}
}
