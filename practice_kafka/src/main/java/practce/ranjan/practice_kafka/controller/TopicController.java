package practce.ranjan.practice_kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practce.ranjan.practice_kafka.service.KafkaService;

@RestController
public class TopicController {

	@Autowired
	private KafkaService kafkaService;
	
	@PostMapping(value="/topic")
	public String createTopic(@RequestParam String topicName, Model model) {
		String response = kafkaService.createTopic(topicName);
		model.addAttribute("response", response);
		return response;
	}
	
	@PostMapping(value="/topic/{name}/message")
	public String produceMessageForTopic(@PathVariable String name, @RequestParam String message, Model model) {
		String response = kafkaService.produceMessage(name, message);
		model.addAttribute("response", response);
		return response;
	}
}
