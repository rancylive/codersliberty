package com.techventura.sentiment;

import java.util.ArrayList;

import com.techventura.sentiment.nlp.Sentiment;
import com.techventura.sentiment.twitter.TweetManager;

import twitter4j.TwitterException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws TwitterException
    {
        ArrayList<String> tweets = TweetManager.getTweets("NEAgainstCAB");
        Sentiment.init();
        int count=1;
        for(String tweet: tweets) {
        	System.out.println("Tweet"+count++);
        	System.out.println(tweet+" : "+Sentiment.analyse(tweet));
        }
    }
}
