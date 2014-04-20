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

		// 提取用户名
		String authorName = targetTD.select("h1 > a").get(0).html();

		// 提取邮件地址
		String email = targetTD.select("h1 > a").get(0).attr("href").split(":")[1];

		// 通过正则表达式提取from和注册时间
		Pattern pattern = Pattern
				.compile("from: ([0-9a-zA-Z]+)\\D+(\\d{4}-\\d{2}-\\d{2})");
		Matcher matcher = pattern.matcher(targetTD.select("i").get(0).html());
		matcher.find();
		String from = matcher.group(1);
		String regTime = matcher.group(2);

		// 提取签名
		String motto = targetTD.select("p").get(0).html();

		Elements tds = targetTD.select("table").get(0).select("td");

		// 提取国籍图案path
		String nationality = tds.get(1).select("img").get(0).attr("src");

		// 提取并转换排名
		int rank = Integer.valueOf(tds.get(3).html());
		// 提取提交题目数
		int submitted = Integer.valueOf(tds.get(5).html());
		// 提取解决题目数
		int solved = Integer.valueOf(tds.get(7).html());
		// 提取提交数
		int submissions = Integer.valueOf(tds.get(9).html());
		// 提取ac数
		int accepted = Integer.valueOf(tds.get(11).html());

		// 提取用户id
		pattern = Pattern.compile("user=([^&]+)&");
		matcher = pattern.matcher(targetTD.select("p").get(1).select("a")
				.get(0).attr("href"));
		matcher.find();
		String Id = matcher.group(1);

		Element neighbortale = targetTD.select("table").get(1);

		// 提取ac率
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
