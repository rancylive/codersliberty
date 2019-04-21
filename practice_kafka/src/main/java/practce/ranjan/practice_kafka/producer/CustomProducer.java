package practce.ranjan.practice_kafka.producer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import practce.ranjan.practice_kafka.constants.IKafkaConstants;

@Component
public class CustomProducer {

	private static KafkaProducer kafkaProducer = null;
	
	public String createTopic(String topicName) {
		Properties config = new Properties();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);

		AdminClient admin = AdminClient.create(config);

		Map<String, String> configs = new HashMap();
		int partitions = 1;
		short replication = 1;

		admin.createTopics(Arrays.asList(new NewTopic(topicName, partitions, replication).configs(configs)));
		return topicName + " created.";
	}

	public String produce(String topic, String message) {
		ProducerRecord record = new ProducerRecord(topic, message);
		initializeProducer();
		kafkaProducer.send(record);
		return message + " published in topic " + topic;
	}
	
	public static void initializeProducer() {
		if(kafkaProducer!=null) 
			return;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        kafkaProducer = new KafkaProducer(props);
    }
}
