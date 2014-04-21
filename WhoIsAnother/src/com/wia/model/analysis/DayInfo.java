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
		if (field == Calendar.DAY_OF_YEAR) {
			return dayValue.get(Calendar.DAY_OF_YEAR);
		} else {
			return dayValue.get(Calendar.DAY_OF_MONTH);
		}

	}

	/**
	 * @return ������Ŀ����
	 */
	public Collection<Problem> getProblemList() {
		return problems.values();
	}

	/**
	 * @return һ�����ύ��Ŀ��
	 */
	public int getSubmittedProblemCount() {
		return problems.size();
	}

	/**
	 * @return Set< pid > һ�����ύ��Ŀ��pid��Ψһ��
	 */
	public Set<Integer> getSubmittedProblemSet() {
		return problems.keySet();
	}

	/**
	 * @return Set< pid > һ�����ύ��Ŀ��pid��Ψһ��
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
		return problems.keySet();
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
