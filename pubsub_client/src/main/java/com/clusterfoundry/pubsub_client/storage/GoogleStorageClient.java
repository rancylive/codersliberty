package com.clusterfoundry.pubsub_client.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

@Component
public class GoogleStorageClient {
	private static Storage storage;
	
	@Autowired
	private Environment environment;

	public Bucket createBucket(String bucketName) throws FileNotFoundException, IOException {
		if (storage == null) {
			initStorage();
		}
		Bucket bucket;
		if ((bucket = storage.get(bucketName)) == null) {
			bucket = storage.create(BucketInfo.of(bucketName));
		}
		return bucket;
	}

	public void listBuckets() throws FileNotFoundException, IOException {
		if (storage == null) {
			initStorage();
		}
		System.out.println("Buckets:");
		Page<Bucket> buckets = storage.list();
		for (Bucket bucket : buckets.iterateAll()) {
			System.out.println(bucket.toString());
		}
	}

	public void initStorage() throws FileNotFoundException, IOException {
		String jsonPath = environment.getProperty("google.cloud.credential.file");
		String projectId = environment.getProperty("google.cloud.project.id");
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
	}
	
	public String getFormatedPath(String bucketName) {
		return "gs://"+bucketName+"/";
	}
}
