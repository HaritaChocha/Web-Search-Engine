package com.webCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlToText {
	
	public static File textFolder = new File("D:\\Code\\Java\\Advanced Algorithm\\_WebSearchEngine\\TextFolder");
//	File webFolder = new File("web-pages");
//	File[] webPages = webFolder.listFiles();
	private static void jSoup(File[] webPages, File textFolder) {

		if (webPages.length > 0 && !textFolder.exists()) {
			textFolder.mkdir();
		}

		for (File file : webPages) {
			try {
				String html = fileToText(file.getAbsolutePath());
				Document doc = Jsoup.parse(html);
				FileWriter fileWriter = new FileWriter(textFolder.getPath() + "\\" + file.getName().replace(".htm", ".txt"));
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
				bufferWriter.write(doc.text());
				bufferWriter.close();
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
			File file = new File(inputFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			while ((str = bufferReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		File webFolder = new File("web-pages");
		File[] webPages = webFolder.listFiles();
		jSoup(webPages, textFolder);
	}

}
