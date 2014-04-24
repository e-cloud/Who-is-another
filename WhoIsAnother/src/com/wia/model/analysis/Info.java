/**
 * 
 */
package com.wia.model.analysis;

import com.wia.model.data.SubmitLog;

/**
 * @author Saint Scott
 * 
 */
public abstract class Info {

	/**
	 * 提交题目数
	 */
	protected int problemSubmitted = 0;

	/**
	 * 解决题目数
	 */
	protected int problemSolved = 0;
	/**
	 * 提交次数
	 */
	protected int submissions = 0;
	/**
	 * 通过次数
	 */
	protected int accepted = 0;

	/**
	 * 标志提交的题目
	 */
	public static final int SUBMIT = 0;
	/**
	 * 标志解决的题目
	 */
	public static final int SOLVE = 1;
	/**
	 * 标志提交
	 */
	public static final int SUBMISSION = 2;
	/**
	 * 标志通过
	 */
	public static final int ACCEPT = 3;

	/**
	 * 添加一个submitLog，一般通过树枝的传递存到叶节点DayInfo里面
	 * 
	 * @param submitLog
	 */
	protected abstract void add(SubmitLog submitLog);

	/**
	 * 计算出对象所有非 final 的简单 field 的值
	 */
	protected abstract void calculateFields();

	/**
	 * 获取对象指定field的值
	 * 
	 * @param field
	 * <br/>
	 *            Acceptable fields:<br/>
	 *            --Info.SUBMIT<br/>
	 *            --Info.SOLVE<br/>
	 *            --Info.SUBMISSION<br/>
	 *            --Info.ACCEPT
	 * @return count
	 */
	public int get(int field) {
		int result = 0;
		switch (field) {
		case SUBMIT:
			result = problemSubmitted;
			break;
		case SOLVE:
			result = problemSolved;
			break;
		case SUBMISSION:
			result = submissions;
			break;
		case ACCEPT:
			result = accepted;
			break;
		default:
			throw new NullPointerException("we doesn't have that field.");
		}
		return result;
	}
}
