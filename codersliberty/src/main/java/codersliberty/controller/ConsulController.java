package codersliberty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codersliberty.consul.ReadConsulValue;

@Controller
public class ConsulController {

	@Autowired
	private ReadConsulValue consulReader;
	
	@RequestMapping(value="/consul")
	public String readConsulValue(@RequestParam String method) {
		if(method.equals("env")) {
			return consulReader.readConsulUsingEnvironment();
		} else {
			return consulReader.readTopic();
		}
	}
}
