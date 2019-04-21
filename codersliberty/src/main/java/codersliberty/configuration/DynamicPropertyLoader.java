package codersliberty.configuration;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.stereotype.Component;

@Component
public class DynamicPropertyLoader {
	PropertiesConfiguration config;

	@PostConstruct
	public void init() throws ConfigurationException {
		config = new PropertiesConfiguration("application.properties");
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
	}

	public PropertiesConfiguration getConfig() {
		return config;
	}

	public String getProperty(String key) {
		String value = null;
		Object prop = this.config.getProperty(key);
		if (prop instanceof String) {
			value = (String) prop;
		} else if (prop instanceof List) {
			value = (String) ((List) prop).get(0);
		}
		return value;
	}
	
	public String getProperty(String type, String key) {
		Properties prop = config.getProperties(type);
		return prop.getProperty(key);
	}
}
