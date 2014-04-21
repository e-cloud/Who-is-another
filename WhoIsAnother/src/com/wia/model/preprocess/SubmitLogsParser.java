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
		// ��ȡ����Ҫ���ݵĸ���ǩ
		Elements targetTR = doc.select("body > table > tbody >tr").get(3)
				.select("td > div > table > tbody > tr");

		if (targetTR.size() <= 1) {
			return null;
		}
		// ͨ��������ȡ��ǰҳ�������submitLog����
		List<SubmitLog> submitLogs = new ArrayList<SubmitLog>();
		Iterator<Element> iterator = targetTR.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			// ��ȡ��ǰtr��td����
			Elements tds = iterator.next().children();
			// ��ȡ����id
			int rid = Integer.valueOf(tds.get(0).html());
			// ��ȡ�ύʱ��
			String datestr = tds.get(1).html();
			// ��ȡ�ж�״̬
			JudgeStatus status = tds.get(2).child(0).html().equals("Accepted") ? JudgeStatus.AC
					: JudgeStatus.WA;
			// ��ȡ��Ŀid
			int pid = Integer.valueOf(tds.get(3).child(0).html());
			// ��ȡִ��ʱ��
			int execTime = Integer.valueOf(tds.get(4).html().split("M")[0]);
			// ��ȡ�����ڴ�
			int execMemory = Integer.valueOf(tds.get(5).html().split("K")[0]);
			// ��ȡ���볤��
			int codeLength = Integer.valueOf(tds.get(6).html().split("B")[0]);
			// ��ȡ�������
			String language = tds.get(7).html();
			// ��submitLogs��Ӻϳɵ�submitLog
			SubmitLog submitLog = new SubmitLog(rid, pid, datestr, status,
					execTime, execMemory, codeLength, language);

			submitLogs.add(submitLog);
		}
		return submitLogs;
	}

}
