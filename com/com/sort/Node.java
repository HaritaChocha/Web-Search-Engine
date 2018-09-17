package com.sort;

import java.util.TreeMap;

public class Node implements Comparable {

	public static int DocID;
	public static int freq;

	public Node(int DocID, int freq) {
		this.DocID = DocID;
		this.freq = freq;
	}

	public static int getDocID() {
		return DocID;
	}

	public static void setDocID(int docID) {
		DocID = docID;
	}

	public static int getFreq() {
		return freq;
	}

	public static void setFreq(int freq) {
		Node.freq = freq;
	}

	@Override
	public int compareTo(Object node) {
		int frequency = ((Node) node).getFreq(); 
		return frequency-this.freq;
	}
	
	@Override
	public String toString() {
        return "[ DocID=" + DocID + ", Frequency=" + freq + "]";
    }
}
