package com.sort;

import com.invertedIndex.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Sort {

	static List<Node> nodeArray;

	static TreeMap<Integer, Integer> localTreeMap;

	public static List<Map.Entry<Integer, Integer>> createNodeArray(TreeMap<Integer, Integer> invertedIndex) {
		List<Map.Entry<Integer, Integer>> list = null;
		if (invertedIndex != null) {
			list = new LinkedList<Map.Entry<Integer, Integer>>(invertedIndex.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

				@Override
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					int x = o1.getValue();
					int y = o2.getValue();
					return (x < y) ? 1 : ((x == y) ? 0 : -1);
				}
			});

			//System.out.println(list);
		} else {
			System.out.println("Inverted Index is Null");
		}

		return list;
	}
}