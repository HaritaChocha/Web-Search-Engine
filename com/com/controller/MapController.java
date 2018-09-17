package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.invertedIndex.InvertedIndex;
import com.trie.TrieCreator;
import com.webCrawler.*;
import com.sort.*;

public class MapController {

	// Maps each HTML and Text files to an index
	public static Map<Integer, List<String>> indexPage = new HashMap<Integer, List<String>>();
	public static File textFolder = new File("D:\\Code\\Java\\Advanced Algorithm\\_WebSearchEngine\\TextFolder");
	public static File webFolder = new File("D:\\Code\\Java\\Advanced Algorithm\\_WebSearchEngine\\web-pages");

	public static Map<Integer, List<String>> getIndexPage() {
		return indexPage;
	}

	public static void createIndexPage() {

		System.out.println("Hello");
		if (textFolder.exists() && webFolder.exists()) {
			File[] textFiles = textFolder.listFiles();
			File[] webFiles = webFolder.listFiles();

			// For loop
			for (int i = 0; i < textFiles.length; i++) {
				List<String> temp = new ArrayList<String>();
				temp.add(textFiles[i].getName());
				temp.add(webFiles[i].getName());
				indexPage.put(i, temp);
			}
		}
	}

	// Returns list of text files
	public static List<String> getTextFiles() {
		List<String> textFiles = new ArrayList<String>();

		for (int i = 0; i < indexPage.size(); i++) {
			List<String> temp = new ArrayList<>();
			temp = indexPage.get(i);
			textFiles.add(temp.get(0));
		}
		return textFiles;
	}

	// Returns list of HTML files
	public static List<String> getHtmlFiles() {
		List<String> htmlFiles = new ArrayList<String>();

		for (int i = 0; i < indexPage.size(); i++) {
			List<String> temp = new ArrayList<>();
			temp = indexPage.get(i);
			htmlFiles.add(temp.get(1));
		}
		return htmlFiles;
	}

	public static String getWebPage(int DocID) {
		List<String> list = indexPage.get(DocID);
		return list.get(1);
	}

	public static List<String> printPages(List<Map.Entry<Integer, Integer>> sortedList) {
		List<String> web = new ArrayList<String>();

		for (Entry<Integer, Integer> map : sortedList) {
			web.add(getWebPage(map.getKey()));
		}
		// for (String s : web)
		// System.out.println(s);

		return web;
	}

	// Main Class
	// public static void main(String args[]) {
	// String value = "protocol";
	// List<Map.Entry<Integer, Integer>> sortedList;
	//
	// // Create Index Page
	// createIndexPage();
	//
	// try {
	// // Create Trie Structure
	// TrieCreator.createTrie();
	// TreeMap<Integer, Integer> localTreeMap =
	// InvertedIndex.getNormalizedInvertedIndex().get(value);
	//
	// // Sorting the map w.r.t frequency
	// sortedList = Sort.createNodeArray(localTreeMap);
	// System.out.println(sortedList);
	// printPages(sortedList);
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// }

	public static List<String> getStarted(String query) {

		List<String> web = new ArrayList<String>();

		List<Map.Entry<Integer, Integer>> sortedList;
		createIndexPage();

		try {
			TrieCreator.createTrie();
			System.out.println(InvertedIndex.InvertedIndex.toString());
			TreeMap<Integer, Integer> localTreeMap = new TreeMap<Integer, Integer>();
			System.out.println(localTreeMap.toString());
			if (!InvertedIndex.InvertedIndex.containsKey(query)) {
				web.add("No Results Found");
				return web;
			} else {
				localTreeMap = InvertedIndex.InvertedIndex.get(query);
				sortedList = Sort.createNodeArray(localTreeMap);
				web = printPages(sortedList);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (String s : web) {
			System.out.println(s);
		}
		return web;

	}
}
