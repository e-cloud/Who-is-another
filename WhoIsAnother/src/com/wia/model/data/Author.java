/**
 * 
 */
package com.wia.model.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class Author {

	private final String authorID;
	private String authorName;
	private String email;
	private String from;
	private String nationality;
	private Calendar registrationTime;
	private String motto;
	private int rank;
	private int submitted;
	private int solved;
	private int submissions;
	private int accepted;
	private double ACRatio;
	private final Map<Integer, Problem> problemMap;
	private Map<String, Author> neighbourMap;

	public Author(String authorID) {
		this.authorID = authorID;
		problemMap = new ConcurrentHashMap<Integer, Problem>();
	}

	public void add(SubmitLog submitLog) {
		int key = submitLog.getPid();
		if (problemMap.containsKey(key)) {
			// if (problemMap.get(key) == null) {
			// Problem problem = new Problem(key);
			// problemMap.put(key, problem);
			// }
			problemMap.get(key).addSubmitLog(submitLog);
		} else {
			Problem problem = new Problem(key);
			problem.addSubmitLog(submitLog);
			problemMap.put(key, problem);
		}

	}

	public void add(List<SubmitLog> submitLogs) {
		for (SubmitLog submitLog : submitLogs) {
			add(submitLog);
		}
	}

	/**
	 * @return the solvedProblemList
	 */
	public Map<Integer, Problem> getProblemMap() {
		problemMap.keySet();
		return problemMap;
	}

	/**
	 * @return the neighbourList
	 */
	public Map<String, Author> getNeighbourMap() {
		return neighbourMap;
	}

	/**
	 * @param neighbourMap
	 *            the neighbourMap to set
	 */
	public void setNeighbourMap(Map<String, Author> neighbourMap) {
		this.neighbourMap = neighbourMap;
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @param registrationTime
	 *            the registrationTime to set
	 */
	public void setRegistrationTime(Calendar registrationTime) {
		this.registrationTime = registrationTime;
	}

	/**
	 * @param motto
	 *            the motto to set
	 */
	public void setMotto(String motto) {
		this.motto = motto;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @param submitted
	 *            the submitted to set
	 */
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	/**
	 * @param solved
	 *            the solved to set
	 */
	public void setSolved(int solved) {
		this.solved = solved;
	}

	/**
	 * @param submissions
	 *            the submissions to set
	 */
	public void setSubmissions(int submissions) {
		this.submissions = submissions;
	}

	/**
	 * @param accepted
	 *            the accepted to set
	 */
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	/**
	 * @param aCRatio
	 *            the aCRatio to set
	 */
	public void setACRatio(double acRatio) {
		ACRatio = acRatio;
	}

	/**
	 * @return the authorID
	 */
	public String getAuthorID() {
		return authorID;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return the registrationTime
	 */
	public Date getRegistrationTime() {
		if (registrationTime == null) {
			return null;
		} else {
			return registrationTime.getTime();
		}

	}

	/**
	 * @return the motto
	 */
	public String getMotto() {
		return motto;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return the submitted
	 */
	public int getSubmitted() {
		return submitted;
	}

	/**
	 * @return the solved
	 */
	public int getSolved() {
		return solved;
	}

	/**
	 * @return the submissions
	 */
	public int getSubmissions() {
		return submissions;
	}

	/**
	 * @return the accepted
	 */
	public int getAccepted() {
		return accepted;
	}

	/**
	 * @return the aCRatio
	 */
	public double getACRatio() {
		return ACRatio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "AuthorID:\t"
				+ authorID
				+ "\nAuthorName:\t"
				+ authorName
				+ "\nEmail:\t"
				+ email
				+ "\nFrom:\t"
				+ from
				+ "\nNationality:\t"
				+ nationality
				+ "\nRegistrationTime:\t"
				+ ThreadLoaclDateFormatUtil
						.formatSimpleDate(getRegistrationTime()) + "\nMotto:\t"
				+ motto + "\nRank:\t" + rank + "\nSubmitted:\t" + submitted
				+ "\nSolved:\t" + solved + "\nSubmissions:\t" + submissions
				+ "\nAccepted:\t" + accepted + "\nAcRatio:\t" + ACRatio + "\n";
	}

}
