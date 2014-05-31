/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

import com.wia.model.data.Author;
import com.wia.model.data.Problem;
import com.wia.model.data.SubmitLog;

/**
 * @author 123
 * 
 */
public class GeneralInfo extends Info {

	private final Author author;
	private final Map<Integer, YearInfo> years;

	/**
	 * 
	 */
	public GeneralInfo(Author author) {
		// TODO Auto-generated constructor stub
		this.author = author;
		years = new HashMap<Integer, YearInfo>();
		init();
	}

	public Author getRefAuthor() {
		return author;
	}

	/**
	 * 初始化GeneralInfo，传入数据，并计算所有节点的简单field的值
	 */
	private void init() {
		Collection<Problem> problems = author.getProblemMap().values();

		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			Collection<SubmitLog> submitLogs = problem.getSubmitMap().values();

			for (Iterator<SubmitLog> iterator2 = submitLogs.iterator(); iterator2
					.hasNext();) {
				add(iterator2.next());
			}
		}
		calculateFields();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.analysis.Info#calculateFields()
	 */
	@Override
	protected void calculateFields() {
		// TODO Auto-generated method stub
		Set<Integer> submitSet = new HashSet<Integer>();
		Set<Integer> solveSet = new HashSet<Integer>();
		for (Iterator<YearInfo> iterator = years.values().iterator(); iterator
				.hasNext();) {
			YearInfo year = iterator.next();
			year.calculateFields();
			submissions += year.get(Info.SUBMISSION);
			accepted += year.get(Info.ACCEPT);
			submitSet.addAll(year.getProblemSet(Info.SUBMIT));
			solveSet.addAll(year.getProblemSet(Info.SOLVE));
		}
		problemSubmitted = submitSet.size();
		problemSolved = solveSet.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.analysis.Info#add(com.wia.model.data.SubmitLog)
	 */
	@Override
	protected void add(SubmitLog submitLog) {
		// TODO Auto-generated method stub
		int key = submitLog.getSubmitTime().get(Calendar.YEAR);
		if (years.containsKey(key)) {
			years.get(key).add(submitLog);
		} else {
			YearInfo yearInfo = new YearInfo(key);
			yearInfo.add(submitLog);
			years.put(key, yearInfo);
		}
	}

	/**
	 * 获得每年每月每天的pair对象的list，键是日期，值是指定field的值
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 * @return a list of Pair mapping that date and the relevant field's value<br/>
	 *         like List< Pair < date, count > >
	 */
	public List<Pair<Date, Integer>> getPairList(int field) {
		// TODO Auto-generated method stub
		List<Pair<Date, Integer>> list = new ArrayList<>();
		for (Iterator<YearInfo> iterator = years.values().iterator(); iterator
				.hasNext();) {
			YearInfo year = iterator.next();
			list.addAll(year.getPairList(field));
		}
		return list;
	}

	/**
	 * 获取某一天的提交题目列表
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return problem 集合
	 */
	public Collection<Problem> getProblemList(int year, int month, int day) {
		return years.get(year).getProblemList(month, day);
	}

	/**
	 * 获取某年相应 field 的值
	 * 
	 * @param year
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return count
	 */
	public int getProblemCount(int year, int field) {
		return years.get(year).get(field);
	}

	/**
	 * 获取每一年中相应 field 的值的map
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< year, count >
	 */
	public Map<Integer, Integer> getProblemCountPerYear(int field) {
		Map<Integer, Integer> map = new HashMap<>();
		for (Iterator<YearInfo> iterator = years.values().iterator(); iterator
				.hasNext();) {
			YearInfo year = iterator.next();
			map.put(year.getYear(), year.get(field));
		}
		return map;
	}

	/**
	 * 获取某年中每个月相应 field 的值的map
	 * 
	 * @param year
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< month, count >
	 */
	public Map<Integer, Integer> getProblemCountPerMonth(int year, int field) {
		return years.get(year).getProblemCountPerMonth(field);
	}

	/**
	 * 获取某年某月中每一天相应 field 的值的map
	 * 
	 * @param year
	 * @param month
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getProblemCountPerDay(int year, int month,
			int field) {
		return years.get(year).getProblemCountPerDay(month, field);
	}

}
