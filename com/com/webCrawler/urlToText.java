package com.webCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class urlToText {
	private static void jSoup(Set<String> set, File textFolder) {
		int counter = 1;
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		for (String str : set) {
			try {

				String html = fileToText(str);
				Document doc = Jsoup.parse(html,"UTF-8");
				FileWriter fileWriter = new FileWriter(textFolder.getPath() + "\\" + Integer.toString(counter) + ".txt");
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
				bufferWriter.write(doc.text());
				bufferWriter.close();
				counter++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("TextFolder Created");
		}
	}

	private static String fileToText(String inputFile) throws FileNotFoundException {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			String str;
			/*
			 * File file = new File(inputFile); FileReader fileReader = new
			 * FileReader(file); BufferedReader bufferReader = new
			 * BufferedReader(fileReader);
			 */
			URL url = new URL(inputFile);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((str = bufferReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			bufferReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		WebCrawler wb = new WebCrawler();
		try {
			wb.algorithm("https://en.wikipedia.org/wiki/Java_(programming_language)");
			//showResults();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(wb.set.size());
		File textFolder = new File("D:\\Code\\Java\\Advanced Algorithm\\_WebSearchEngine\\TextFolder");
		jSoup(wb.set, textFolder);
		System.out.println("Text Created");
	}
}
