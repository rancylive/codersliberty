package com.clusterfoundry.pubsub_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class PubsubClient {
	public static void main(String[] args) {
		SpringApplication.run(PubsubClient.class, args);
	}
}
