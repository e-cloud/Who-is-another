/**
 * 
 */
package com.wia.model.analysis;

import com.wia.model.data.SubmitLog;

/**
 * @author 123
 * 
 */
public abstract class Info {
	protected int submittedProblems;
	protected int solvedProblems;
	protected int submissions;
	protected int accepted;

	/**
	 * @param submitLog
	 */
	protected abstract void add(SubmitLog submitLog);

}
