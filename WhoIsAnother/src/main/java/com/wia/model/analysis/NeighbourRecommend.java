/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wia.model.data.Author;
import com.wia.model.preprocess.AuthorPidListCrawler;

/**
 * @author acer
 * 
 */
public class NeighbourRecommend {

	private final Author author;

	public NeighbourRecommend(Author author) {
		this.author = author;
	}

	public List<Integer> recommend() {
		Set<Integer> authorPidList = author.getProblemMap().keySet();
		List<Author> neighbours = author.getNeighbourList();
		List<Set<Integer>> neighborsPidList = new AuthorPidListCrawler(
				neighbours).crawl();

		Set<Integer> temp = new HashSet<Integer>();

		for (int i = 0; i < neighborsPidList.size(); i++) {

			temp.addAll(neighborsPidList.get(i)); // 六位邻居做的题并操作
		}

		temp.removeAll(authorPidList); // 差操作，得到六位邻居中除去了自己已做的pid集合
		List<Integer> neighborRecommandList = new ArrayList<Integer>(temp);

		return neighborRecommandList;
	}

}
