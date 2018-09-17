package com.invertedIndex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.trie.TrieST;

public class InvertedIndex {

	public static TreeMap<String, TreeMap<Integer, Integer>> InvertedIndex = new TreeMap<String, TreeMap<Integer, Integer>>();
	static TreeMap<Integer, Integer> totalWordCount = new TreeMap<Integer, Integer>();

	static boolean flag = true;

	public static void createInvertedIndex(TreeMap<String, TreeMap<Integer, Integer>> trieMap, int size, int DocID) {
		if (InvertedIndex.isEmpty()) {
			InvertedIndex.putAll(trieMap);
			totalWordCount.put(DocID, size);
		} else {
			for (String key : trieMap.keySet()) {
				if (InvertedIndex.containsKey(key)) {
					TreeMap<Integer, Integer> tempTM = trieMap.get(key);
					InvertedIndex.get(key).putAll(tempTM);

				}
			}
			totalWordCount.put(DocID, size);
		}
	}

	public static TreeMap<String, TreeMap<Integer, Integer>> getInvertedIndex(){
		return InvertedIndex;
	}

	public static int getTotalCount(int DocID) {
		return totalWordCount.get(DocID);
	}

}
