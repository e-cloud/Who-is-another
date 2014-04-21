/**
 * 
 */
package com.wia.model.analysis;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.wia.Context;
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
	private final Context context;

	/**
	 * 
	 */
	public GeneralInfo() {
		// TODO Auto-generated constructor stub
		context = Context.getInstance();
		this.author = context.getCurrentAuthor();
		years = new HashMap<Integer, YearInfo>();
		init();
	}

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
	 * ��ȡĳһ����ύ��Ŀ�б�
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return problem ����
	 */
	public Collection<Problem> getProblemList(int year, int month, int day) {

		return years.get(year).getProblemList(month, day);
	}

	/**
	 * ��ȡĳ����ÿһ���ύ����Ŀ��
	 * 
	 * @param year
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerDay(int year) {
		return years.get(year).getSubmittedProblemCountPerDay();
	}

	/**
	 * ��ȡĳ����ÿ�����ύ����Ŀ��
	 * 
	 * @param year
	 * @return Map< month, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerMonth(int year) {
		return years.get(year).getSubmittedProblemCountPerMonth();
	}

	/**
	 * ��ȡĳ��ĳ����ÿһ���ύ����Ŀ��
	 * 
	 * @param year
	 * @param month
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCountPerDay(int year,
			int month) {
		return years.get(year).getSubmittedProblemCountPerDay(month);
	}

	/**
	 * ��ȡĳ����ÿһ��������Ŀ��
	 * 
	 * @param year
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerDay(int year) {
		return null;
	}

	/**
	 * ��ȡĳ����ÿ���½������Ŀ��
	 * 
	 * @param year
	 * @return Map< month, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerMonth(int year) {
		return null;
	}

	/**
	 * ��ȡĳ��ĳ����ÿһ��������Ŀ��
	 * 
	 * @param year
	 * @param month
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerDay(int year, int month) {
		return null;
	}

}
