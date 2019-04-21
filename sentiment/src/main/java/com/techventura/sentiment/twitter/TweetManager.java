package com.techventura.sentiment.twitter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetManager {
	public static ArrayList<String> getTweets(String topic) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		ArrayList<String> tweetList = new ArrayList<String>();
		Query query = new Query(topic);
		QueryResult queryResult;
		do {
			queryResult = twitter.search(query);
			List<Status> tweets = queryResult.getTweets();
			tweets.forEach(status -> {
				tweetList.add(status.getText());
			});
		} while ((query = queryResult.nextQuery()) != null);
		return tweetList;
	}
}
