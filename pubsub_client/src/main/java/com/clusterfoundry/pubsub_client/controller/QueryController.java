package com.clusterfoundry.pubsub_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.clusterfoundry.pubsub_client.service.QueryService;
import com.clusterfoundry.pubsub_client.vo.Query;

@Controller
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value="/query/submit", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String submitQuery(@RequestParam String firstQuery, @RequestParam String secondQuery, Model model) throws Exception {
		Query query=new Query();
		query.setFirst(firstQuery);
		query.setSecond(secondQuery);
		System.out.println("Received "+query);
		queryService.publishQuery(query);
		model.addAttribute("query",query);
		return "home";
	}
}
