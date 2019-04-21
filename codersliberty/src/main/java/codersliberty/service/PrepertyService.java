package codersliberty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codersliberty.configuration.DynamicPropertyLoader;

@Service
public class PrepertyService {

	@Autowired
	private DynamicPropertyLoader propertyLoader;

	public String read(String key) {
		String value = propertyLoader.getProperty(key);
		System.out.println("Property read for "+key+" as "+value);
		return value;
	}
}
