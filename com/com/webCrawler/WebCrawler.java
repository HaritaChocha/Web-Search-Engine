package com.webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	public static Queue<String> queue = new LinkedList<String>();
	public static Set<String> set = new HashSet<String>();
	public static String urlRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	//public static String urlRegex = "(?<=\")[^\"]+\\.htm(?=\")";
	
	public static void algorithm(String root) throws IOException {
		queue.add(root);
		boolean flag = false;
		//System.out.println(set.size());
		while (!queue.isEmpty() && set.size()<100)  {
			String crawledURL = queue.poll();
			URL url = null;
			BufferedReader bufferReader = null;
			if (set.size() > 100)
				return;
			
			while (!flag) {
				try {
					url = new URL(crawledURL);
					bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
					flag = true;
				} catch (MalformedURLException e) {
					System.out.println("Malfunctioned URL : " + crawledURL);
					crawledURL = queue.poll();
					flag = false;
				} catch (IOException e) {
					System.out.println("IOException URL : " + crawledURL);
					crawledURL = queue.poll();
					flag = false;
				} finally {
					if (bufferReader != null)
						bufferReader.close();
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			String temp;
			bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((temp = bufferReader.readLine()) != null) {
				stringBuilder.append(temp);
			}

			temp = stringBuilder.toString();
			Pattern pattern = Pattern.compile(urlRegex);
			Matcher matcher = pattern.matcher(temp);

			while (matcher.find() && set.size()<100) {
				String matchString = matcher.group();

				if (!set.contains(matchString)) {
					set.add(matchString);
					queue.add(matchString);
				}
			}
		}
	}

	public static void showResults() {
		int counter = 0;
		for (String s : set) {
			System.out.println(counter +" " + s);
			counter++;
		}
	}

	public static void main(String[] args) {
		try {
			algorithm("https://www.oracle.com/ca-en/java/index.html");
			showResults();
			System.out.println("Size of set : " + set.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
