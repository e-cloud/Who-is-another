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

import javafx.util.Pair;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.preprocess.AuthorPidListCrawler;
import com.wia.model.preprocess.TopAuthorsCrawler;

/**
 * @author Saint Scott
 * 
 */
public class ACERecommend {

	private final Author author;

	public ACERecommend(Author author) {
		this.author = author;
	}

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

	private void removeDuplicate(Map<Integer, Integer> data) {
		for (Iterator<Integer> iterator = author.getProblemMap().keySet()
				.iterator(); iterator.hasNext();) {
			Integer pid = iterator.next();
			data.remove(pid);
		}
	}

	/**
	 * @param author
	 *            目标用户
	 * @param rcmdSize
	 *            推荐题目数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pair<Integer, Integer>> recommend(int rcmdSize) {
		Context context = Context.getInstance();

		if (!context.containsKey("acercmdmap")) {
			List<Author> authors = new TopAuthorsCrawler(100).crawl();

			List<Set<Integer>> setList = new AuthorPidListCrawler(authors)
					.crawl();

			Map<Integer, Integer> map = calculate(setList);
			context.addContextObject("acercmdmap", map);
		}
		Map<Integer, Integer> map = (Map<Integer, Integer>) context
				.getContextObject("acercmdmap");
		removeDuplicate(map);

		List<Pair<Integer, Integer>> pairs = sort(map);

		return pairs.subList(0, rcmdSize);
	}
}
