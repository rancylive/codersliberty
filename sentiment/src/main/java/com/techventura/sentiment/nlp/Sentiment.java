package com.techventura.sentiment.nlp;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class Sentiment {
	static StanfordCoreNLP coreNLP;

	public static void init() {
		Properties properties = new Properties();
		properties.put("annotators", "tokenize, ssplit, parse, sentiment");
		coreNLP = new StanfordCoreNLP(properties);
	}

	public static int analyse(String text) {
		int mainSentiment = 0;
		if (text != null && text.length() > 0) {
			int longest = 0;
			Annotation annotation = coreNLP.process(text);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence.get(SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}
			}
		}
		return mainSentiment;
	}
	
	public static void main(String[] args) {
		String text = "Inception is one of the best movies of all time. I think everyone should watch it";
		String text1 = "Inception is considered one of the worst movies of all time. But I found it like a normal movie";
		String text2 = "The food was very bad";
		String text3 = "The food was awesome";
		String text4 = "The food was very good";
		init();
		System.out.println(analyse(text3));
	}
}
