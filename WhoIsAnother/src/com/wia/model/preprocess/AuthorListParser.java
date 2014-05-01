/**
 * 
 */
package com.wia.model.preprocess;

import java.util.ArrayList;
import java.util.List;
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
public class AuthorListParser {

	/**
	 * @param data
	 */
	public static List<Author> parse(String data) {
		Document doc = Jsoup.parse(data);
		Elements targetTRs = doc.select("body > table > tbody >tr").get(3)
				.select("td").get(1).select("table").last().select("tr");
		List<Author> authorMap = new ArrayList<>();

		targetTRs.remove(0);
		Pattern pattern = Pattern.compile("user=([^&]+)&*");
		for (Element element : targetTRs) {

			Matcher matcher = pattern.matcher(element.select("td > a").attr(
					"href"));
			matcher.find();
			String Id = matcher.group(1);

			// 提取ac率
			String ratio = element.select("td").get(6).html();
			double acRatio = Double.valueOf(ratio.substring(0,
					ratio.lastIndexOf('%')));

			int nbrank = Integer.valueOf(element.select("td").get(0).html());
			String nbnationality = element.select("td > img").get(0)
					.attr("src");
			// 提取用户名
			String nbauthorName = element.select("td > a").get(0).html();
			// 提取签名
			String nbmotto = element.select("td").get(3).html();

			// 提取解决题目数
			int nbsolved = Integer.valueOf(element.select("td").get(4).text());
			// 提取提交题目数
			int nbsubmissions = Integer.valueOf(element.select("td").get(5)
					.text());
			Author author = new Author(Id);
			author.setRank(nbrank);
			author.setAuthorName(nbauthorName);
			author.setSubmissions(nbsubmissions);
			author.setSolved(nbsolved);
			author.setMotto(nbmotto);
			author.setNationality(nbnationality);
			author.setACRatio(acRatio);
			authorMap.add(author);
		}
		return authorMap;
	}
}
