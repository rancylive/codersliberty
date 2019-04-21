package com.clusterfoundry.pubsub_client.subscriber;

import java.io.IOException;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.pubsub.PubsubUtils;
import org.apache.spark.streaming.pubsub.SparkGCPCredentials;
import org.apache.spark.streaming.pubsub.SparkPubsubMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.clusterfoundry.pubsub_client.storage.GoogleStorageClient;
import com.google.cloud.ServiceOptions;
import com.google.cloud.storage.Bucket;

@Component
public class CustomSubscriber {
	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	@Autowired
	private Environment environment;

	@Autowired
	private GoogleStorageClient storageClient;

	public void subscribe(String topic_id) {
		String credentialsFilePath = environment.getProperty("google.cloud.credential.file");
		String thriftUri = environment.getProperty("hive.metastore.uris");
		String sparkWarehouseDir = environment.getProperty("spark.sql.warehouse.dir");
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("Pubshub");
		SparkSession session = SparkSession.builder().config(conf).config("spark.sql.warehouse.dir", sparkWarehouseDir)
				.config("hive.metastore.uris", thriftUri).enableHiveSupport().getOrCreate();
		/*
		 * session.sparkContext().hadoopConfiguration().set("fs.gs.impl",
		 * "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFileSystem");
		 * session.sparkContext().hadoopConfiguration().set(
		 * "fs.AbstractFileSystem.gs.impl",
		 * "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFS");
		 */
		JavaSparkContext javaSparkContext = JavaSparkContext.fromSparkContext(session.sparkContext());

		JavaStreamingContext javaStreamingContext = new JavaStreamingContext(javaSparkContext, Durations.seconds(60));

		SparkGCPCredentials gcpCredentials = new SparkGCPCredentials.Builder().jsonServiceAccount(credentialsFilePath)
				.build();
		System.out.println("gcpCredentials: " + gcpCredentials);

		JavaReceiverInputDStream<SparkPubsubMessage> lines = PubsubUtils.createStream(javaStreamingContext, PROJECT_ID,
				topic_id, gcpCredentials, StorageLevel.MEMORY_AND_DISK_2());

		lines.foreachRDD(line -> {
			List<SparkPubsubMessage> content = line.collect();
			content.forEach(msg -> {
				String query = new String(msg.getData());
				System.out.println("PubSub Message: " + query);
				Dataset<Row> dataset = session.sql(query);
				// dataset.show();
				try {
					Bucket bucket = storageClient.createBucket("pubsub_out");
					dataset.write().mode(SaveMode.Append).save(storageClient.getFormatedPath(bucket.getName()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
		/* JavaDStream<Object> msgs = lines.map(msg -> new String(msg.getData())); */
		javaStreamingContext.start();

	}
}
