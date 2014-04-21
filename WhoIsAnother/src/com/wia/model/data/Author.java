/**
 * 
 */
package com.wia.model.data;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class Author {

	private final String authorID;
	private final String authorName;
	private final String email;
	private final String from;
	private final String nationality;
	private final Calendar registrationTime;
	private final String motto;
	private final int rank;
	private final int submitted;
	private final int solved;
	private final int submissions;
	private final int accepted;
	private final double ACRatio;
	private final Map<Integer, Problem> problemMap;
	private Map<Integer, Author> neighbourMap;

	public Author(String authorID, String authorName, String email,
			String from, String registrationTime, String motto,
			String nationality, int rank, int submitted, int solved,
			int submissions, int accepted, double acRatio)
			throws ParseException {
		this.authorID = authorID;
		this.authorName = authorName;
		this.email = email;
		this.from = from;
		this.registrationTime = Calendar.getInstance();
		this.registrationTime.setTime(ThreadLoaclDateFormatUtil
				.parseSimple(registrationTime));
		this.motto = motto;
		this.nationality = nationality;
		this.rank = rank;
		this.submitted = submitted;
		this.solved = solved;
		this.submissions = submissions;
		this.accepted = accepted;
		this.ACRatio = acRatio;
		problemMap = new HashMap<Integer, Problem>();
	}

	public void add(SubmitLog submitLog) {
		int key = submitLog.getPid();
		if (problemMap.containsKey(key)) {
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
		return problemMap;
	}

	/**
	 * @return the neighbourList
	 */
	public Map<Integer, Author> getNeighbourMap() {
		return neighbourMap;
	}

	/**
	 * @param neighbourMap
	 *            the neighbourList to set
	 */
	public void setNeighbourList(Map<Integer, Author> neighbourMap) {
		this.neighbourMap = neighbourMap;
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
		return registrationTime.getTime();
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
