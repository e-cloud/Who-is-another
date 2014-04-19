/**
 * 
 */
package com.wia.model.data;

import java.text.ParseException;
import java.util.Calendar;

import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class SubmitLog {

	public enum JudgeStatus {
		AC, WA
	}

	private final Calendar submitTime;
	private final JudgeStatus status;
	private final int execTime;
	private final int execMemory;
	private final int codeLength;
	private final String language;

	/**
	 * @throws ParseException
	 * 
	 */
	public SubmitLog(String datestr, JudgeStatus status, int exectime,
			int execmemory, int codelength, String language)
			throws ParseException {
		// TODO Auto-generated constructor stub
		submitTime = Calendar.getInstance();
		submitTime.setTime(ThreadLoaclDateFormatUtil.parseDetail(datestr));
		this.status = status;
		this.execTime = exectime;
		this.execMemory = execmemory;
		this.codeLength = codelength;
		this.language = language;
	}

	/**
	 * @return the submitTime
	 */
	public Calendar getSubmitTime() {
		return submitTime;
	}

	/**
	 * @return the status
	 */
	public JudgeStatus getStatus() {
		return status;
	}

	/**
	 * @return the execTime
	 */
	public int getExecTime() {
		return execTime;
	}

	/**
	 * @return the execMemory
	 */
	public int getExecMemory() {
		return execMemory;
	}

	/**
	 * @return the codeLength
	 */
	public int getCodeLength() {
		return codeLength;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

}
