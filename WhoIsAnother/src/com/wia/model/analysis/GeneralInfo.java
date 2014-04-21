/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

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

	private volatile static GeneralInfo generalInfo;

	/**
	 * 
	 */
	private GeneralInfo() {
		// TODO Auto-generated constructor stub
		context = Context.getInstance();
		this.author = context.getCurrentAuthor();
		years = new HashMap<Integer, YearInfo>();
		init();
	}

	public static GeneralInfo getInstance() {
		if (generalInfo == null) {
			synchronized (Context.class) {
				if (generalInfo == null) {
					generalInfo = new GeneralInfo();
				}
			}
		}
		return generalInfo;
	}

	public void reset() {
		generalInfo = new GeneralInfo();
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
	 * ��ȡĳ�����ύ����Ŀ��
	 * 
	 * @param year
	 * @return Map< day, count >
	 */
	public int getSubmittedProblemCount(int year) {
		return years.get(year).getSubmittedProblemSet().size();
	}

	/**
	 * ��ȡĳ���н������Ŀ��
	 * 
	 * @param year
	 * @return Map< day, count >
	 */
	public int getSolvedProblemCount(int year) {
		return years.get(year).getSubmittedProblemSet().size();
	}

	/**
	 * ��ȡÿһ�����ύ����Ŀ��
	 * 
	 * @return Map< year, count >
	 */
	public Map<Integer, Integer> getSubmittedProblemCount() {
		Map<Integer, Integer> map = new HashMap<>();
		for (Iterator<Integer> iterator = years.keySet().iterator(); iterator
				.hasNext();) {
			int key = iterator.next();
			map.put(key, getSubmittedProblemCount(key));
		}
		return map;
	}

	/**
	 * ��ȡÿһ���н������Ŀ��
	 * 
	 * @return Map< year, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCount() {
		Map<Integer, Integer> map = new HashMap<>();
		for (Iterator<Integer> iterator = years.keySet().iterator(); iterator
				.hasNext();) {
			int key = iterator.next();
			map.put(key, getSolvedProblemCount(key));
		}
		return map;
	}

	/**
	 * ��ȡĳ����ÿһ��������Ŀ��
	 * 
	 * @param year
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerDay(int year) {
		return years.get(year).getSolvedProblemCountPerDay();
	}

	/**
	 * ��ȡĳ����ÿ���½������Ŀ��
	 * 
	 * @param year
	 * @return Map< month, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerMonth(int year) {
		return years.get(year).getSolvedProblemCountPerMonth();
	}

	/**
	 * ��ȡĳ��ĳ����ÿһ��������Ŀ��
	 * 
	 * @param year
	 * @param month
	 * @return Map< day, count >
	 */
	public Map<Integer, Integer> getSolvedProblemCountPerDay(int year, int month) {
		return years.get(year).getSolvedProblemCountPerDay(month);
	}

	/**
	 * ��ȡĳ����ÿһ��������Ŀ��
	 * 
	 * @return Map< day, count >
	 */
	public List<Pair<Date, Integer>> getSubmittedProblemCountEveryDay() {
		List<Pair<Date, Integer>> submitlist = new ArrayList<>();
		for (Iterator<YearInfo> iterator = years.values().iterator(); iterator
				.hasNext();) {
			YearInfo yearInfo = iterator.next();
			submitlist.addAll(yearInfo.getSubmittedProblemCountEveryDay());
		}
		return submitlist;
	}

	/**
	 * ��ȡĳ����ÿһ��������Ŀ��
	 * 
	 * @return Map< day, count >
	 */
	public List<Pair<Date, Integer>> getSolvedProblemCountEveryDay() {
		List<Pair<Date, Integer>> solvedlist = new ArrayList<>();
		for (Iterator<YearInfo> iterator = years.values().iterator(); iterator
				.hasNext();) {
			YearInfo yearInfo = iterator.next();
			solvedlist.addAll(yearInfo.getSubmittedProblemCountEveryDay());
		}
		return solvedlist;
	}
}