package com.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.controller.MapController;
import com.webCrawler.*;

public class TrieCreator {

	public static TreeMap<String,TreeMap<Integer,Integer>> trieMap = new TreeMap<String,TreeMap<Integer,Integer>>();
	
	
	// Create Trie and create TreeMap for specific page
	public static void createTrie() throws FileNotFoundException{
		System.out.println("creating trie");
		File textFolder = MapController.textFolder;
		List<String> textFiles = MapController.getTextFiles();
		
		for (int i = 0; i <textFiles.size()  ; i++) {
			String textFile = fileToText(textFolder.getAbsolutePath() +"\\"+ textFiles.get(i));
			TrieST.createTrie(textFile, trieMap, i);
		}
		System.out.println("Trie Created");
	}
	
	// Copy content of a text file to string
	private static String fileToText(String inputFile) throws FileNotFoundException {
		
		StringBuilder stringBuilder = new StringBuilder();
		try {
			String str;
			File file = new File(inputFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			while((str = bufferReader.readLine())!=null) {
				stringBuilder.append(str);
			}
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
