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
	protected MonthInfo(int year, int month) {
		// TODO Auto-generated constructor stub
		this.monthValue = Calendar.getInstance();
		monthValue.set(year, month, 1);
		this.days = new HashMap<Integer, DayInfo>();
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
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo day = iterator.next();
			day.calculateFields();
			submissions += day.get(Info.SUBMISSION);
			accepted += day.get(Info.ACCEPT);
			submitSet.addAll(day.getProblemSet(Info.SUBMIT));
			solveSet.addAll(day.getProblemSet(Info.SOLVE));
		}
		problemSubmitted = submitSet.size();
		problemSolved = solveSet.size();
	}

	/**
	 * 获取所属公元年份值
	 * 
	 * @return
	 */
	protected int getYear() {
		return monthValue.get(Calendar.YEAR);
	}

	/**
	 * 获取月份值
	 * 
	 * @return
	 */
	protected int getMonth() {
		return monthValue.get(Calendar.MONTH);
	}

	/**
	 * 获得每天的pair对象的list，键是日期，值是指定field的值
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
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo day = iterator.next();
			list.add(day.getPair(field));
		}
		return list;
	}

	/**
	 * 一个月里指定field对应的pid的唯一集
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 * @return Set < pid > <br/>
	 *         a set contains the whole <b>month's</b> problem ID of the
	 *         relevant field
	 */
	protected Set<Integer> getProblemSet(int field) {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();
			set.addAll(dayInfo.getProblemSet(field));
		}
		return set;
	}

	/**
	 * 获取某一天的提交题目列表
	 * 
	 * @param day
	 * @return problem 集合
	 */
	protected Collection<Problem> getProblemList(int day) {
		return days.get(day).getProblemList();
	}

	/**
	 * 获取每一天相应 field 的值的map
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 * @return Map< day, count >
	 */
	protected Map<Integer, Integer> getProblemCountPerDay(int field) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Iterator<DayInfo> iterator = days.values().iterator(); iterator
				.hasNext();) {
			DayInfo dayInfo = iterator.next();
			map.put(dayInfo.getDay(Calendar.DAY_OF_MONTH), dayInfo.get(field));
		}
		return map;
	}

}
