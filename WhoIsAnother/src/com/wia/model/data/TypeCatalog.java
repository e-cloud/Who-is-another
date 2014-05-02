/**
 * 
 */
package com.wia.model.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wia.model.local.TypeCatalogGenerator;

/**
 * @author Saint Scott
 * 
 */
public class TypeCatalog {

	private Map<Integer, String> catalog;
	private volatile static TypeCatalog uniqueInstance;

	private TypeCatalog() {
		// TODO read the default classification file and initialize the catalog
		TypeCatalogGenerator generator = new TypeCatalogGenerator();
		try {
			this.catalog = generator.generateCatalogMap(getClass().getResource(
					"/com/wia/model/local/DefaultCatalog.json").getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TypeCatalog getInstance() {
		if (uniqueInstance == null) {
			synchronized (TypeCatalog.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new TypeCatalog();
				}
			}
		}
		return uniqueInstance;
	}

	public void reset(Map<Integer, String> catalog) {
		this.catalog = catalog;
	}

	public Map<Integer, String> getCatalog() {
		return catalog;
	}

	/**
	 * 输入一个题目的id，返回该题目对应的分类
	 * 
	 * @param pid
	 * @return the problem's type
	 */
	public String getType(int pid) {
		return catalog.get(pid);
	}

	/**
	 * 输入要分类的pid集合，返回每个分类的对应题目数
	 * 
	 * @param pidSet
	 *            a set of pid (no duplicate)
	 * @return Map&lt;typename, count&gt;
	 */
	public Map<String, Integer> count(Collection<Integer> pidCollection) {
		Map<String, Integer> pairs = new HashMap<>();
		for (Iterator<Integer> iterator = pidCollection.iterator(); iterator
				.hasNext();) {
			Integer pid = iterator.next();
			String type = getType(pid);
			Integer count = pairs.get(type);
			pairs.put(type, count == null ? 1 : count + 1);
		}
		return pairs;
	}

	/**
	 * 输入要分类的pid集合，返回分类后的关联数组
	 * 
	 * @param pidSet
	 *            a set of pid (no duplicate)
	 * @return Map&lt;typename, List&lt;pid&gt;&gt;
	 */
	public Map<String, List<Integer>> classify(Collection<Integer> pidSet) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (Iterator<Integer> iterator = pidSet.iterator(); iterator.hasNext();) {
			Integer pid = iterator.next();
			String type = getType(pid);
			if (map.containsKey(type)) {
				map.get(type).add(pid);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(pid);
				map.put(type, list);
			}
		}
		return map;
	}
}
