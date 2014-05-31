/**
 * 
 */
package com.wia.model.preprocess;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wia.model.data.Author;
import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class AuthorInfoParser {

	/**
	 * @param data
	 * @return
	 */
	private static Set<Integer> parse(String data) {
		// 提取pid集合
		Set<Integer> pidSet = new HashSet<Integer>();
		Pattern pattern = Pattern.compile("p\\((\\d+),\\d+,\\d+\\)");
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			pidSet.add(Integer.valueOf(matcher.group(1)));
		}
		return pidSet;
	}

	/**
	 * @param data
	 * @param author
	 * @throws ParseException
	 */
	public static Set<Integer> parse(String data, Author author)
			throws ParseException {
		// TODO Auto-generated method stub
		if (data.contains("System Message")
				|| data.contains("<DIV>No such user.</DIV>")) {
			return null;
		}

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
				.compile("from:\\s(.+)\\sregistered on\\s(\\d{4}-\\d{2}-\\d{2})");
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

		// 提取pid集合
		Set<Integer> pidSet = parse(targetTD.select("p").html());

		double acRatio = 0;
		Elements targetTRs = targetTD.select("table").get(1).select("tr");
		for (Element element : targetTRs) {
			if (element.select("td").get(0).html().equals(String.valueOf(rank))) {
				String ratio = element.select("td").get(6).html();
				if (ratio.length() > 1) {
					acRatio = Double.valueOf(ratio.substring(0,
							ratio.lastIndexOf('%')));
				}
				break;
			}
		}
		author.setAccepted(accepted);
		author.setAuthorName(authorName);
		author.setEmail(email);
		author.setFrom(from);
		author.setMotto(motto);
		author.setNationality(nationality);
		author.setRank(rank);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ThreadLoaclDateFormatUtil.parseSimple(regTime));
		author.setRegistrationTime(calendar);
		author.setSubmissions(submissions);
		author.setSolved(solved);
		author.setSubmitted(submitted);
		author.setACRatio(acRatio);

		return pidSet;
	}

}
