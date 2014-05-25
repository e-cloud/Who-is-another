/**
 * 
 */
package com.wia.model.analysis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javafx.util.Pair;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.Problem;
import com.wia.model.data.SubmitLog;

/**
 * @author 123
 * 
 */
public class LightSpot {
	private final int[] acceptedTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	private final int[] unsolvedTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	private final int[] submitTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
	Author author;
	int ACInFirstSubmit;

	/**
	 * 构造函数，并初始化author对象
	 */
	public LightSpot() {
		Context context = Context.getInstance();
		this.author = context.getCurrentAuthor();
		this.ACInFirstSubmit = 0;
		init();
	}

	/**
	 * @return 24个时段解决题目数的数组，下标即时段
	 */
	public int[] getAcceptedTimeInterval() {
		return this.acceptedTimeInterval;
	}

	/**
	 * @return 24个时段提交题目数的数组，下标即时段
	 */
	public int[] getSubmitTimeInterval() {
		return this.submitTimeInterval;
	}

	/**
	 * @return 第一次提交就解决的题目数
	 */
	public int ACInFirstSubmit() {
		return ACInFirstSubmit;
	}

	/**
	 * 必须先调用这个函数才能调用getAcceptedTimeInterval（），getSubmitTimeInterval（）函数！！！！！！
	 */
	public void init() {
		Collection<Problem> problems = author.getProblemMap().values();
		Iterator<Problem> problemIterator = problems.iterator();
		while (problemIterator.hasNext()) {
			Problem p = problemIterator.next();
			Iterator<SubmitLog> submitIterator = p.getSubmitMap().values()
					.iterator();
			List<SubmitLog> list = new ArrayList<SubmitLog>();
			Iterator<SubmitLog> log = p.getSubmitMap().values().iterator();
			while (log.hasNext()) {
				SubmitLog l = log.next();
				list.add(l);
			}
			int min = list.get(0).getRid();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getRid() < min)
					min = list.get(i).getRid();
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getRid() == min)
					if (list.get(i).getStatus().toString() == "AC")
						ACInFirstSubmit++;
			}
			while (submitIterator.hasNext()) {
				SubmitLog s = submitIterator.next();
				if (s.getStatus().toString() == "AC") {
					int index = s.getSubmitTime().get(Calendar.HOUR_OF_DAY);
					acceptedTimeInterval[index]++;
				} else {
					int index = s.getSubmitTime().get(Calendar.HOUR_OF_DAY);
					unsolvedTimeInterval[index]++;
				}
			}
		}
		for (int i = 0; i < 24; i++) {
			submitTimeInterval[i] = acceptedTimeInterval[i]
					+ unsolvedTimeInterval[i];
		}
		int p = 5;
	}

	/**
	 * @return 解决问题数最多的10天
	 */
	public List<Pair<String, Integer>> problemSolvedTop10() {
		// public List<Integer> problemSolvedTop10() {
		// new SimpleDateFormat().format(new Date());
		SimpleDateFormat stdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Pair<Date, Integer>> tempProblemSolvedTop10;
		List<Pair<String, Integer>> getProblemSolvedTop10 = new ArrayList<Pair<String, Integer>>();
		GeneralInfo generalInfo = GeneralInfo.getInstance();
		tempProblemSolvedTop10 = generalInfo.getPairList(Info.SOLVE);
		// 对每一天的题目数进行排序
		Collections.sort(tempProblemSolvedTop10,
				new Comparator<Pair<Date, Integer>>() {
					@Override
					public int compare(Pair<Date, Integer> p1,
							Pair<Date, Integer> p2) {
						if (p1.getValue() > p2.getValue())
							return -1;
						else
							return 0;
					}
				});
		// 取出前十天
		if (tempProblemSolvedTop10.size() > 10) {
			for (int i = 0; i < 10; i++) {
				Pair<Date, Integer> p = tempProblemSolvedTop10.get(i);
				Pair<String, Integer> p1 = new Pair<String, Integer>(
						stdf.format(p.getKey()), p.getValue());
				getProblemSolvedTop10.add(p1);
			}
		} else {
			for (int i = 0; i < tempProblemSolvedTop10.size(); i++) {
				Pair<Date, Integer> p = tempProblemSolvedTop10.get(i);
				Pair<String, Integer> p1 = new Pair<String, Integer>(
						stdf.format(p.getKey()), p.getValue());
				getProblemSolvedTop10.add(p1);
			}
		}
		return getProblemSolvedTop10;
	}

	/**
	 * @return 加入杭电多少天
	 */
	public int howManyDays() {
		Date register = author.getRegistrationTime();
		Date date = Calendar.getInstance().getTime();
		long days = date.getTime() - register.getTime();
		days = days / (1000 * 60 * 60 * 24);
		return (int) days;
	}

}
