package codersliberty.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
//@RefreshScope
public class ReadConsulValue {
	/**
	 * Reads value from property. In case of consul, populate it using 
	 * "curl -X PUT localhost:8500/v1/kv/config/helloservice/kafka.topic -d AOtoMAPR"
	 * 
	 * TODO Unable to read from consul. Explore how to populate key value in consul.
	 */
	@Value("${kafka.topic}")
	private String kafkaTopic;

	@Autowired
	private Environment environment;
	
	public String readTopic() {
		return kafkaTopic;
	}
	public String readConsulUsingEnvironment() {
		return environment.getProperty("kafka.topic");
	}
}
