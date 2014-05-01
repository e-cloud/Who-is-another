/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javafx.util.Pair;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;
import com.wia.model.test.ACERecommendTest;

/**
 * @author Saint Scott
 * 
 */
public class ACERecommend {
	private static Logger logger = Logger.getLogger(ACERecommendTest.class
			.getName());

	private Map<Integer, Integer> calculate(List<Set<Integer>> setList) {

		Map<Integer, Integer> data = new HashMap<Integer, Integer>();

		for (Iterator<Set<Integer>> iterator = setList.iterator(); iterator
				.hasNext();) {
			Set<Integer> pidSet = iterator.next();
			for (Iterator<Integer> iterator2 = pidSet.iterator(); iterator2
					.hasNext();) {
				Integer key = iterator2.next();
				Integer val = data.get(key);
				data.put(key, val == null ? 1 : val + 1);
			}
		}

		return data;
	}

	private List<Pair<Integer, Integer>> sort(Map<Integer, Integer> data) {

		List<Pair<Integer, Integer>> result = new ArrayList<>();

		for (Iterator<Entry<Integer, Integer>> iterator = data.entrySet()
				.iterator(); iterator.hasNext();) {
			Map.Entry<Integer, Integer> pair = iterator.next();
			result.add(new Pair<Integer, Integer>(pair.getKey(), pair
					.getValue()));
		}

		Collections.sort(result, new Comparator<Pair<Integer, Integer>>() {

			@Override
			public int compare(Pair<Integer, Integer> o1,
					Pair<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() < o2.getValue()) {
					return 1;
				} else if (o1.getValue() == o2.getValue()) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		return result;
	}

	private void removeDuplicate(Author author, Map<Integer, Integer> data) {
		for (Iterator<Integer> iterator = author.getProblemMap().keySet()
				.iterator(); iterator.hasNext();) {
			Integer pid = iterator.next();
			data.remove(pid);
		}
	}

	public List<Integer> recommand(Author author, int rcmdSize) {
		DataPreprocessor preprocessor = new DataPreprocessor();

		List<Author> authors = preprocessor.retrieveTopAuthors(100);

		List<Set<Integer>> setList = preprocessor
				.rertrieveSimpleAuthorList(authors);

		Map<Integer, Integer> map = calculate(setList);

		removeDuplicate(author, map);

		List<Pair<Integer, Integer>> pairs = sort(map);
		logger.info(pairs.toString());
		List<Integer> rcmdList = new ArrayList<>();
		for (int i = 0; i < rcmdSize; i++) {
			rcmdList.add(pairs.get(i).getKey());
		}
		return rcmdList;
	}
}
