package com.cosineSimilarity;

import java.util.TreeMap;

import com.invertedIndex.*;

public class TfIdf {
	
	public static TreeMap<Integer, Double> wordVector = new TreeMap<Integer, Double>();
	public static final int TOTAL = com.invertedIndex.InvertedIndex.getInvertedIndex().size();
	
	public static TreeMap<Integer, Double> tfIdf(String word, TreeMap<String, TreeMap<Integer, Integer>> InvertedIndex, TreeMap<Integer, Integer> totalWordCount) {
		TreeMap <Integer, Integer> temp = new TreeMap <Integer, Integer>();
		temp = InvertedIndex.get(word);
		for(Integer key : temp.keySet()) {
			double tf = tfCalculator(totalWordCount.get(key), temp.get(key));
			double idf = idfCalculator(temp.get(key));
			double tfidf = tf*idf;
			wordVector.put(key, tfidf);
		}
		return wordVector;
	}

	private static double idfCalculator(Integer word) {
		return 1  + Math.log(TOTAL / word );
	}

	private static double tfCalculator(Integer totalWords, Integer word) {
		return totalWords*word;
	}
	
}
