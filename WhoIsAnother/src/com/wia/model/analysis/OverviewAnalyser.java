/**
 * 
 */
package com.wia.model.analysis;

import java.util.Iterator;
import java.util.List;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.Problem;
import com.wia.model.data.SubmitLog;

/**
 * @author 123
 *
 */
public class OverviewAnalyser {
	private Author currentAuthor;
	
	/**
	 * 构造函数，获取当前作者，以初始化currentAuthor
	 */
	public OverviewAnalyser(){
		Context context = Context.getInstance();
		this.currentAuthor = context.getCurrentAuthor();
	}
	
	/**
	 * 输入年份，获得该年的全部题目列表、解决的题目数和提交的题目数
	 * @param year 输入年份
	 * @param solved 解决的题目数
	 * @param submit 提交的题目数
	 * @return yearProblem,输入的年份的全部题目列表
	 */
	List<Problem> getGeneralOverview(int year,int solved,int submit){
		int unsolved = 0;
		boolean flag = false;
		List<Problem> yearProblem;
		List<Problem> problemList = currentAuthor.getSolvedProblemAAList();
		List<Problem> unSolvedProblemList = currentAuthor.getUnSolvedProblemList();
		problemList.addAll(unSolvedProblemList);	//获得该年全部题目列表
		Iterator<Problem> ite = problemList.iterator();
		while(ite.hasNext()){
			Problem p_Problem = ite.next();
			List<SubmitLog> submitLog = p_Problem.getSubmitList();		//该题目提交记录列表
			Iterator<SubmitLog> iter = submitLog.iterator();
			while(iter.hasNext()){
				SubmitLog p_Submit = iter.next();
				if(p_Submit.getSubmitTime().YEAR==year){
					yearProblem.add(p_Problem);
					if(p_Submit.getStatus().toString()=="AC"){
					 	flag = true;
					}
				}
			}
			if(flag)
				solved++;
			else
				unsolved++;
		}
		submit = solved + unsolved;
		return yearProblem;
	}
	
	List<Problem> getYearOverview(List<Problem> yearProblem,int month,int solved,int submit){
		int unsolved = 0;
		boolean flag = false;
		List<Problem> monthProblem;
		//List<Problem> problemList = currentAuthor.getSolvedProblemAAList();
		//List<Problem> unSolvedProblemList = currentAuthor.getUnSolvedProblemList();
		//problemList.addAll(unSolvedProblemList);
		Iterator<Problem> ite = yearProblem.iterator();
		while(ite.hasNext()){
			Problem p_Problem = ite.next();
			List<SubmitLog> submitLog = p_Problem.getSubmitList();
			Iterator<SubmitLog> iter = submitLog.iterator();
			while(iter.hasNext()){
				SubmitLog p_Submit = iter.next();
				if(p_Submit.getSubmitTime().MONTH==month){
					monthProblem.add(p_Problem);
					if(p_Submit.getStatus().toString()=="AC"){
					 	flag = true;
					}
				}
			}
			if(flag)
				solved++;
			else
				unsolved++;
		}
		submit = solved + unsolved;
		return monthProblem;
	}
	
	List<Problem> getMonthOverview(List<Problem> monthProblem,int day,int solved,int submit){
		int unsolved = 0;
		boolean flag = false;
		List<Problem> dayProblem;
		//List<Problem> problemList = currentAuthor.getSolvedProblemAAList();
		//List<Problem> unSolvedProblemList = currentAuthor.getUnSolvedProblemList();
		//problemList.addAll(unSolvedProblemList);
		Iterator<Problem> ite = monthProblem.iterator();
		while(ite.hasNext()){
			Problem p_Problem = ite.next();
			List<SubmitLog> submitLog = p_Problem.getSubmitList();
			Iterator<SubmitLog> iter = submitLog.iterator();
			while(iter.hasNext()){
				SubmitLog p_Submit = iter.next();
				if(p_Submit.getSubmitTime().DAY_OF_MONTH==day){
					dayProblem.add(p_Problem);
					if(p_Submit.getStatus().toString()=="AC"){
					 	flag = true;
					}
				}
			}
			if(flag)
				solved++;
			else
				unsolved++;
		}
		submit = solved + unsolved;
		return dayProblem;
	}
	
	void getDayOverview(List<Problem> dayProblem,List<Integer> pid,List<Integer> submissions,List<String> status){
		boolean flag = false;
		int count;
		//List<Problem> problemList = currentAuthor.getSolvedProblemAAList();
		Iterator<Problem> ite = dayProblem.iterator();
		while(ite.hasNext()){
			Problem p_Problem = ite.next();
			pid.add(p_Problem.getPid());
			List<SubmitLog> submitLog = p_Problem.getSubmitList();
			Iterator<SubmitLog> iter = submitLog.iterator();
			count = 1;
			while(iter.hasNext()){
				count++;
				SubmitLog p_Submit = iter.next();
				if(p_Submit.getStatus().toString()=="AC"){
					 flag = true;
				}
			}
			submissions.add(count);
			if(flag)
				status.add("AC");
			else
				status.add("WA");
		}
	}
	
}
