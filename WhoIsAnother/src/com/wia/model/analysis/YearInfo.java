/**
 * 
 */
package com.wia.model.analysis;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	public YearInfo(int year) {
		// TODO Auto-generated constructor stub
		this.yearValue = Calendar.getInstance();
		this.yearValue.set(Calendar.YEAR, year);
		this.months = new HashMap<Integer, MonthInfo>();
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return yearValue.get(Calendar.YEAR);
	}

	/**
	 * ��ȡĳһ����ύ��Ŀ�б�
	 * 
	 * @param month
	 * @param day
	 * @return problem ����
	 */
	public Collection<Problem> getProblemList(int month, int day) {

		return months.get(month).getProblemList(day);
	}

	/**
	 * ��ȡĳ����ÿһ���ύ����Ŀ��
	 * 
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerDay() {
		Map<Integer, Integer> submitMap = new HashMap<Integer, Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo monthInfo = iterator.next();
			submitMap.putAll(monthInfo
					.getSubmittedProblemCountPerDay(Calendar.DAY_OF_YEAR));
		}
		return submitMap;
	}

	/**
	 * ��ȡĳ����ÿ�����ύ����Ŀ��
	 * 
	 * @return Map< month, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerMonth() {
		Map<Integer, Integer> submitMap = new HashMap<Integer, Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo monthInfo = iterator.next();
			submitMap.put(monthInfo.getMonth(), monthInfo
					.getSubmittedProblemSet().size());
		}
		return submitMap;
	}

	/**
	 * @return Set< pid >
	 */
	public Set<Integer> getSubmittedProblemSet() {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<MonthInfo> iterator = months.values().iterator(); iterator
				.hasNext();) {
			MonthInfo monthInfo = iterator.next();
			set.addAll(monthInfo.getSubmittedProblemSet());
		}
		return set;
	}

	/**
	 * ��ȡĳ��ĳ����ÿһ���ύ����Ŀ��
	 * 
	 * @param month
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerDay(int month) {
		return months.get(month).getSubmittedProblemCountPerDay(
				Calendar.DAY_OF_MONTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.analysis.Info#add(com.wia.model.data.SubmitLog)
	 */
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

}
