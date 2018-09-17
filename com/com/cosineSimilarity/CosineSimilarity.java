package com.cosineSimilarity;

import java.util.TreeMap;

public class CosineSimilarity {

	
	static TreeMap<Integer, Double> wordVector;
			
	public static void getVector(String word, TreeMap<String, TreeMap<Integer, Integer>> InvertedIndex, TreeMap<Integer, Integer> totalWordCount) {
		wordVector = TfIdf.tfIdf(word, InvertedIndex, totalWordCount);
	}
	public static void getCosineSimilarity() {
		
	}
}
