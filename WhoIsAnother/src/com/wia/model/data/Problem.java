/**
 * 
 */
package com.wia.model.data;

import java.util.Calendar;
import java.util.List;

/**
 * @author Saint Scott
 * 
 */
public class Problem {
	private final int pid;
	private String linkURL;
	private List<SubmitLog> submitList;
	boolean isSolved;
	private Calendar solvedTime;

	/**
	 * 
	 */
	public Problem(int pid) {
		// TODO Auto-generated constructor stub
		this.pid = pid;
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
		return linkURL;
	}

	/**
	 * @param linkURL
	 *            the linkURL to set
	 */
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}

	/**
	 * @return the submitList
	 */
	public List<SubmitLog> getSubmitList() {
		return submitList;
	}

	/**
	 * @param submitList
	 *            the submitList to set
	 */
	public void setSubmitList(List<SubmitLog> submitList) {
		this.submitList = submitList;
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
