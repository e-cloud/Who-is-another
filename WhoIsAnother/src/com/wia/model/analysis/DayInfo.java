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
	public DayInfo(int year, int month, int day) {
		this.dayValue = Calendar.getInstance();
		this.dayValue.set(year, month, day);
		problems = new HashMap<Integer, Problem>();
	}

	/**
	 * @return
	 */
	public int getDay(int field) {
		return dayValue.get(field);
	}

	public Date getDate() {
		return dayValue.getTime();
	}

	/**
	 * @return 该天题目集合
	 */
	public Collection<Problem> getProblemList() {
		return problems.values();
	}

	/**
	 * @return 一天里提交题目数
	 */
	public int getSubmittedProblemCount() {
		return problems.size();
	}

	/**
	 * @return 一天里提交题目数
	 */
	public int getSolvedProblemCount() {
		return getSolvedProblemSet().size();
	}

	/**
	 * @return Set< pid > 一天里提交题目的pid的唯一集
	 */
	public Set<Integer> getSubmittedProblemSet() {
		return problems.keySet();
	}

	/**
	 * @return Set< pid > 一天里提交题目的pid的唯一集
	 */
	public Set<Integer> getSolvedProblemSet() {
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

}
