package codersliberty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import codersliberty.service.PrepertyService;

@Controller
public class PropertyController {

	@Autowired
	private PrepertyService prop;
	
	@RequestMapping(value="/api/property", method=RequestMethod.GET)
	public String gerProeprty(@RequestParam String key, Model model) {
		model.addAttribute("ack", prop.read(key));
		return "acknowledgement";
	}
}
