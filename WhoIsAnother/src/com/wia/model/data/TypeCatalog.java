/**
 * 
 */
package com.wia.model.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

import com.wia.Context;

/**
 * @author Saint Scott
 * 
 */
public class TypeCatalog {

	private Map<Integer, String> catalog;
	private volatile static TypeCatalog uniqueInstance;

	private TypeCatalog() {
		// TODO read the default classification file and initialize the catalog
		// instance
	}

	public static TypeCatalog getInstance() {
		if (uniqueInstance == null) {
			synchronized (Context.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new TypeCatalog();
				}
			}
		}
		return uniqueInstance;
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
	 * @return a list of Pair < type, count >
	 */
	public List<Pair<String, Integer>> classify(Set<Integer> pidSet) {
		return null;
	}
}
