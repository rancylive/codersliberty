package codersliberty.service;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class HBConnection {
	public Connection getHbaseConnection() throws IOException {
		Configuration conf = getHbaseConf();
		Connection connection = ConnectionFactory.createConnection(conf);
		return connection;
	}

	public Configuration getHbaseConf() {
		Configuration conf = HBaseConfiguration.create();
		return conf;
	}
}
