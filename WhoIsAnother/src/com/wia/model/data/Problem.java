/**
 * 
 */
package com.wia.model.data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.wia.model.data.SubmitLog.JudgeStatus;

/**
 * @author Saint Scott
 * 
 */
public class Problem {
	private final int pid;
	private final Map<Integer, SubmitLog> submitMap;
	boolean isSolved;
	private Calendar solvedTime;

	/**
	 * 
	 */
	public Problem(int pid) {
		// TODO Auto-generated constructor stub
		this.pid = pid;
		submitMap = new HashMap<Integer, SubmitLog>();
	}

	/**
	 * @return
	 */
	public int getAcceptedCount() {
		return 0;
	}

	/**
	 * @return
	 */
	public boolean isSolved() {
		return isSolved;
	}

	/**
	 * @return the linkURL
	 */
	public String getLinkURL() {
		return "http://acm.hdu.edu.cn/showproblem.php?pid=" + pid;
	}

	/**
	 * @return the submitList
	 */
	public Map<Integer, SubmitLog> getSubmitMap() {
		return submitMap;
	}

	/**
	 * @param submitList
	 *            the submitList to set
	 */
	public void addSubmitLog(SubmitLog submitLog) {
		int key = submitLog.getRid();
		if (!isSolved && submitLog.getStatus() == JudgeStatus.AC) {
			isSolved = true;
		}
		if (submitMap.containsKey(key)) {
			return;
		} else {
			submitMap.put(key, submitLog);
		}
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @return the solvedTime
	 */
	public Calendar getSolvedTime() {
		return solvedTime;
	}
}
