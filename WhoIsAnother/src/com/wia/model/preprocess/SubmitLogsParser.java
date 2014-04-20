/**
 * 
 */
package com.wia.model.preprocess;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wia.model.data.SubmitLog;
import com.wia.model.data.SubmitLog.JudgeStatus;

/**
 * @author Saint Scott
 * 
 */
public class SubmitLogsParser implements DataParser {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.fetcher.DataParser#parse(java.lang.String)
	 */
	@Override
	public List<SubmitLog> parse(String data) throws ParseException {
		// TODO Auto-generated method stub
		Document doc = Jsoup.parse(data);
		// path body > table > tbody > tr[3] > td > div > table > tbody > tr
		// 获取含主要内容的根标签
		Elements targetTR = doc.select("body > table > tbody >tr").get(3)
				.select("td > div > table > tbody > tr");

		if (targetTR.size() <= 1) {
			return null;
		}
		// 通过迭代提取当前页面的所有submitLog对象
		List<SubmitLog> submitLogs = new ArrayList<SubmitLog>();
		Iterator<Element> iterator = targetTR.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			// 获取当前tr的td数组
			Elements tds = iterator.next().children();
			// 提取运行id
			int rid = Integer.valueOf(tds.get(0).html());
			// 提取提交时间
			String datestr = tds.get(1).html();
			// 提取判定状态
			JudgeStatus status = tds.get(2).child(0).html().equals("Accepted") ? JudgeStatus.AC
					: JudgeStatus.WA;
			// 提取题目id
			int pid = Integer.valueOf(tds.get(3).child(0).html());
			// 提取执行时间
			int execTime = Integer.valueOf(tds.get(4).html().split("M")[0]);
			// 提取运行内存
			int execMemory = Integer.valueOf(tds.get(5).html().split("K")[0]);
			// 提取代码长度
			int codeLength = Integer.valueOf(tds.get(6).html().split("B")[0]);
			// 提取编程语言
			String language = tds.get(7).html();
			// 向submitLogs添加合成的submitLog
			submitLogs.add(new SubmitLog(rid, pid, datestr, status, execTime,
					execMemory, codeLength, language));
		}
		return submitLogs;
	}

}
