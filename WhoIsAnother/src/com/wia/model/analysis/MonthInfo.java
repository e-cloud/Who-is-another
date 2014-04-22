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
public class MonthInfo extends Info {

	private final Map<Integer, DayInfo> days;
	private final Calendar monthValue;

	/**
	 * 
	 */
	public MonthInfo(int year, int month) {
		// TODO Auto-generated constructor stub
		this.monthValue = Calendar.getInstance();
		monthValue.set(year, month, 1);
		this.days = new HashMap<Integer, DayInfo>();
	}

	/**
	 * @return
	 */
	public int getYear() {
		return monthValue.get(Calendar.YEAR);
	}

	/**
	 * @return
	 */
	public int getMonth() {
		return monthValue.get(Calendar.MONTH);
	}

	/**
	 * 获取某一天的提交题目列表 *
	 * 
	 * @param day
	 * @return problem 集合
	 */
	public Collection<Problem> getProblemList(int day) {
		return days.get(day).getProblemList();
	}

	/**
	 * 获取某月中每一天提交的题目数链表
	 * 
	 * @return List < Pair< Date, Integer > >
	 */
	public List<Pair<Date, Integer>> getSubmittedProblemCountEveryDay() {
		List<Pair<Date, Integer>> submitlist = new ArrayList<>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();

			submitlist.add(new Pair<Date, Integer>(dayInfo.getDate(), dayInfo
					.getSubmittedProblemCount()));
		}
		return submitlist;
	}

	/**
	 * 获取某月中每一天解决的题目数链表
	 * 
	 * @return List < Pair< Date, Integer > >
	 */
	public List<Pair<Date, Integer>> getSolvedProblemCountEveryDay() {
		List<Pair<Date, Integer>> solvedlist = new ArrayList<>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();

			solvedlist.add(new Pair<Date, Integer>(dayInfo.getDate(), dayInfo
					.getSolvedProblemCount()));
		}
		return solvedlist;
	}

	/**
	 * 获取某月中每一天提交的题目数
	 * 
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerDay(int field) {
		Map<Integer, Integer> submitMap = new HashMap<Integer, Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();

			submitMap.put(dayInfo.getDay(field),
					dayInfo.getSubmittedProblemCount());
		}
		return submitMap;
	}

	/**
	 * @return Set< pid >
	 */
	public Set<Integer> getSubmittedProblemSet() {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();
			set.addAll(dayInfo.getSubmittedProblemSet());
		}
		return set;
	}

	/**
	 * 获取某月中每一天提交的题目数
	 * 
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerDay(int field) {
		Map<Integer, Integer> solvedMap = new HashMap<Integer, Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();

			solvedMap.put(dayInfo.getDay(field),
					dayInfo.getSolvedProblemCount());
		}
		return solvedMap;
	}

	/**
	 * @return Set< pid >
	 */
	public Set<Integer> getSolvedProblemSet() {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();
			set.addAll(dayInfo.getSolvedProblemSet());
		}
		return set;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.analysis.Info#add(com.wia.model.data.SubmitLog)
	 */
	@Override
	protected void add(SubmitLog submitLog) {
		// TODO Auto-generated method stub
		int key = submitLog.getSubmitTime().get(Calendar.DAY_OF_MONTH);
		if (days.containsKey(key)) {
			days.get(key).add(submitLog);
		} else {
			DayInfo dayInfo = new DayInfo(getYear(), getMonth(), key);
			dayInfo.add(submitLog);
			days.put(key, dayInfo);
		}
	}

}
