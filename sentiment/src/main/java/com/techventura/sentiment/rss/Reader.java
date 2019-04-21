package com.techventura.sentiment.rss;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

public class Reader {
	public static void main(String[] args) throws IllegalArgumentException, FeedException, IOException {
		URL indiaToday = new URL("https://www.indiatoday.in/rss/home");
		URL toi = new URL("https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
		SyndFeedInput feedInput=new SyndFeedInput();
		SyndFeed feed=feedInput.build(new InputStreamReader(indiaToday.openStream()));
		String type = feed.getFeedType();
		System.out.println("Type "+type);
		List<SyndEntryImpl> entries = feed.getEntries();
		entries.forEach(action -> {
			System.out.println(action.getTitle());
			System.out.println(action.getDescription().getValue());
			System.out.println(cleanText(action.getDescription().getValue()));
			System.out.println("Read more at "+action.getLink());
			System.out.println();
		});
	}
	
	private static String cleanText(String text) {
	    String cleaned = text;
	    cleaned = cleaned.replace("/(<([^>]+)>)/ig","");
	    cleaned = cleaned.replace("/&#8217;/gi", "\'");
	    cleaned = cleaned.replace("/&#039;/gi", "\'");
	    //cleaned = cleaned.replace("/\[&#8230;\]/gi", "...");
	    return cleaned;
	}
}
