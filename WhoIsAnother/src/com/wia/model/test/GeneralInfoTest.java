/**
 * 
 */
package com.wia.model.test;

import java.util.Map;

import com.wia.model.analysis.GeneralInfo;

/**
 * @author Saint Scott
 * 
 */
public class GeneralInfoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneralInfo info = new GeneralInfo();
		Map<Integer, Integer> map = null;

		map = info.getSolvedProblemCountPerMonth(2007);
		map = info.getSolvedProblemCountPerDay(2007, 2);
		map = info.getSolvedProblemCountPerDay(2007);
		map = info.getSubmittedProblemCountPerMonth(2007);
		map = info.getSubmittedProblemCountPerDay(2007, 2);
		map = info.getSubmittedProblemCountPerDay(2007);

	}

}
