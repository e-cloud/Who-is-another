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

import com.wia.model.data.Problem;
import com.wia.model.data.SubmitLog;

/**
 * @author Saint Scott
 * 
 */
public class YearInfo extends Info {

	private final Map<Integer, MonthInfo> months;
	private final Calendar yearValue;

	/**
	 * 
	 */
	protected YearInfo(int year) {
		// TODO Auto-generated constructor stub
		this.yearValue = Calendar.getInstance();
		this.yearValue.set(Calendar.YEAR, year);
		this.months = new HashMap<Integer, MonthInfo>();
	}

	/**
	 * 获取公元年份值
	 * 
	 * @return the year
	 */
	protected int getYear() {
		return yearValue.get(Calendar.YEAR);
	}

	@Override
	protected void add(SubmitLog submitLog) {
		// TODO Auto-generated method stub
		int key = submitLog.getSubmitTime().get(Calendar.MONTH);

		if (months.containsKey(key)) {
			months.get(key).add(submitLog);
		} else {
			MonthInfo monthInfo = new MonthInfo(getYear(), key);
			monthInfo.add(submitLog);
			months.put(key, monthInfo);
		}
	}

	@Override
	protected void calculateFields() {
		// TODO Auto-generated method stub
		Set<Integer> submitSet = new HashSet<Integer>();
		Set<Integer> solveSet = new HashSet<Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo month = iterator.next();
			month.calculateFields();
			submissions += month.get(Info.SUBMISSION);
			accepted += month.get(Info.ACCEPT);
			submitSet.addAll(month.getProblemSet(Info.SUBMIT));
			solveSet.addAll(month.getProblemSet(Info.SOLVE));
		}
		problemSubmitted = submitSet.size();
		problemSolved = solveSet.size();
	}

	/**
	 * 获得每月每天的pair对象的list，键是日期，值是指定field的值
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 * @return a list of Pair mapping that date and the relevant field's value<br/>
	 *         like List< Pair < date, count > >
	 */
	protected List<Pair<Date, Integer>> getPairList(int field) {
		// TODO Auto-generated method stub
		List<Pair<Date, Integer>> list = new ArrayList<>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo month = iterator.next();
			list.addAll(month.getPairList(field));
		}
		return list;
	}

	/**
	 * 一年里指定field对应的pid的唯一集
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 * @return Set < pid > <br/>
	 *         a set contains the whole <b>year's</b> problem ID of the relevant
	 *         field
	 */
	protected Set<Integer> getProblemSet(int field) {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo monthInfo = iterator.next();
			set.addAll(monthInfo.getProblemSet(field));
		}
		return set;
	}

	/**
	 * 获取某一天的提交题目列表
	 * 
	 * @param month
	 *            range from [0,11]
	 * @param day
	 *            starts from 1
	 * @return problem 集合
	 */
	protected Collection<Problem> getProblemList(int month, int day) {
		return months.get(month).getProblemList(day);
	}

	/**
	 * 获取每个月相应 field 的值的map
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< month, count >
	 */
	protected Map<Integer, Integer> getProblemCountPerMonth(int field) {
		Map<Integer, Integer> solvedMap = new HashMap<Integer, Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo monthInfo = iterator.next();
			int month = monthInfo.getMonth();
			int count = monthInfo.get(field);
			solvedMap.put(month, count);
		}
		return solvedMap;
	}

	/**
	 * 获取某月中每一天相应 field 的值的map
	 * 
	 * @param month
	 *            range from [0,11]
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< day, count >
	 */
	protected Map<Integer, Integer> getProblemCountPerDay(int month, int field) {
		return months.get(month).getProblemCountPerDay(field);
	}

}
