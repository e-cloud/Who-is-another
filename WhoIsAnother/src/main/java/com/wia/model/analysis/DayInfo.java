/**
 * 
 */
package com.wia.model.analysis;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

import com.wia.model.data.Problem;
import com.wia.model.data.SubmitLog;

/**
 * @author 123
 * 
 */
public class DayInfo extends Info {

	private final Map<Integer, Problem> problems;
	private final Calendar dayValue;

	/**
	 * 
	 */
	protected DayInfo(int year, int month, int day) {
		this.dayValue = Calendar.getInstance();
		this.dayValue.set(year, month, day);
		problems = new HashMap<Integer, Problem>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.analysis.Info#add(com.wia.model.data.SubmitLog)
	 */
	@Override
	protected void add(SubmitLog submitLog) {
		// TODO Auto-generated method stub
		int key = submitLog.getPid();
		if (problems.containsKey(key)) {
			problems.get(key).addSubmitLog(submitLog);
		} else {
			Problem problem = new Problem(key);
			problem.addSubmitLog(submitLog);
			problems.put(key, problem);
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
		problemSubmitted = getSubmittedProblemSet().size();
		problemSolved = getSolvedProblemSet().size();
		for (Iterator<Problem> iterator = problems.values().iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			submissions += problem.getSubmitCount();
			accepted += problem.getAcceptedCount();
		}
	}

	/**
	 * 获取指定 field 的值
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields: <b>Calendar's</b> fields
	 * @return
	 */
	protected int getDay(int field) {
		return dayValue.get(field);
	}

	/**
	 * 获取对象对应的日期Date
	 * 
	 * @return date
	 */
	protected Date getDate() {
		return dayValue.getTime();
	}

	/**
	 * 获得一个pair对象，键是日期，值是指定field的值
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 *            --Info.ACCEPT
	 * @return a pair mapping that date and the relevant field's value<br/>
	 *         like Pair < date, count >
	 */
	protected Pair<Date, Integer> getPair(int field) {
		// TODO Auto-generated method stub
		return new Pair<Date, Integer>(getDate(), get(field));
	}

	/**
	 * 一天里所有提交题目的集合
	 * 
	 * @return Collection < Problem >
	 */
	protected Collection<Problem> getProblemList() {
		return problems.values();
	}

	/**
	 * 一天里指定field对应的pid的唯一集
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 * @return Set < pid > <br/>
	 *         a set contains the whole <b>day's</b> problem ID of the relevant
	 *         field
	 */
	protected Set<Integer> getProblemSet(int field) {
		switch (field) {
		case Info.SUBMIT:
			return getSubmittedProblemSet();
		case Info.SOLVE:
			return getSolvedProblemSet();
		default:
			throw new NullPointerException("we doesn't have that set");
		}
	}

	/**
	 * 一天里提交题目的pid的唯一集
	 * 
	 * @return Set< pid >
	 */
	private Set<Integer> getSubmittedProblemSet() {
		return problems.keySet();
	}

	/**
	 * 一天里提交题目的pid的唯一集
	 * 
	 * @return Set< pid >
	 */
	private Set<Integer> getSolvedProblemSet() {
		Set<Integer> set = new HashSet<Integer>();
		for (Iterator<Problem> iterator = problems.values().iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			if (problem.isSolved()) {
				set.add(problem.getPid());
			}
		}
		return set;
	}

}
