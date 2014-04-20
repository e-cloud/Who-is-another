/**
 * 
 */
package com.wia.model.preprocess;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class GeneralAuthorInfoParser implements DataParser {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.fetcher.DataParser#parse(java.lang.String)
	 */
	@Override
	public Object parse(String data) throws ParseException {
		// TODO Auto-generated method stub
		Document doc = Jsoup.parse(data);
		// path body > table > tbody > tr[3] > td > table > tr > td
		Element targetTD = doc.select("body > table > tbody >tr").get(3)
				.select("td").get(1);

		// ��ȡ�û���
		String authorName = targetTD.select("h1 > a").get(0).html();

		// ��ȡ�ʼ���ַ
		String email = targetTD.select("h1 > a").get(0).attr("href").split(":")[1];

		// ͨ��������ʽ��ȡfrom��ע��ʱ��
		Pattern pattern = Pattern
				.compile("from: ([0-9a-zA-Z]+)\\D+(\\d{4}-\\d{2}-\\d{2})");
		Matcher matcher = pattern.matcher(targetTD.select("i").get(0).html());
		matcher.find();
		String from = matcher.group(1);
		String regTime = matcher.group(2);

		// ��ȡǩ��
		String motto = targetTD.select("p").get(0).html();

		Elements tds = targetTD.select("table").get(0).select("td");

		// ��ȡ����ͼ��path
		String nationality = tds.get(1).select("img").get(0).attr("src");

		// ��ȡ��ת������
		int rank = Integer.valueOf(tds.get(3).html());
		// ��ȡ�ύ��Ŀ��
		int submitted = Integer.valueOf(tds.get(5).html());
		// ��ȡ�����Ŀ��
		int solved = Integer.valueOf(tds.get(7).html());
		// ��ȡ�ύ��
		int submissions = Integer.valueOf(tds.get(9).html());
		// ��ȡac��
		int accepted = Integer.valueOf(tds.get(11).html());

		// ��ȡ�û�id
		pattern = Pattern.compile("user=([^&]+)&");
		matcher = pattern.matcher(targetTD.select("p").get(1).select("a")
				.get(0).attr("href"));
		matcher.find();
		String Id = matcher.group(1);

		Element neighbortale = targetTD.select("table").get(1);

		// ��ȡac��
		double acRatio = 0;
		for (Element element : neighbortale.select("tr")) {
			if (element.select("td").get(0).html().equals(String.valueOf(rank))) {
				String ratio = element.select("td").get(6).html();
				acRatio = Double.valueOf(ratio.substring(0,
						ratio.lastIndexOf('%')));
			}
		}

		return new Author(Id, authorName, email, from, regTime, motto,
				nationality, rank, submitted, solved, submissions, accepted,
				acRatio);
	}

}
