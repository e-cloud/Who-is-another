/**
 * 
 */
package com.wia.model.data;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class Author {

	private final String authorID;
	private final String from;
	private final String nationality;
	private final Calendar registrationTime;
	private final String motto;
	private final int rank;
	private final int submitted;
	private final int solved;
	private final int submissions;
	private final int accepted;
	private final int ACRatio;
	private List<Problem> solvedProblemList;
	private List<Problem> unSolvedProblemList;
	private List<Author> neighbourList;

	public Author(String authorID, String from, String registrationTime,
			String motto, String nationality, int rank, int submitted,
			int solved, int submissions, int accepted, int acRatio)
			throws ParseException {
		this.authorID = authorID;
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
	}

	/**
	 * @return the solvedProblemList
	 */
	public List<Problem> getSolvedProblemList() {
		return solvedProblemList;
	}

	/**
	 * @param solvedProblemList
	 *            the solvedProblemList to set
	 */
	public void setSolvedProblemList(List<Problem> solvedProblemList) {
		this.solvedProblemList = solvedProblemList;
	}

	/**
	 * @return the unSolvedProblemList
	 */
	public List<Problem> getUnSolvedProblemList() {
		return unSolvedProblemList;
	}

	/**
	 * @param unSolvedProblemList
	 *            the unSolvedProblemList to set
	 */
	public void setUnSolvedProblemList(List<Problem> unSolvedProblemList) {
		this.unSolvedProblemList = unSolvedProblemList;
	}

	/**
	 * @return the neighbourList
	 */
	public List<Author> getNeighbourList() {
		return neighbourList;
	}

	/**
	 * @param neighbourList
	 *            the neighbourList to set
	 */
	public void setNeighbourList(List<Author> neighbourList) {
		this.neighbourList = neighbourList;
	}

	/**
	 * @return the authorID
	 */
	public String getAuthorID() {
		return authorID;
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
	public Calendar getRegistrationTime() {
		return registrationTime;
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
	public int getACRatio() {
		return ACRatio;
	}

}
