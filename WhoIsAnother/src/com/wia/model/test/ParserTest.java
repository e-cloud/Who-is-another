/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.wia.model.data.Author;
import com.wia.model.data.SubmitLog;
import com.wia.model.preprocess.AuthorInfoParser;
import com.wia.model.preprocess.AuthorListParser;
import com.wia.model.preprocess.Fetcher;
import com.wia.model.preprocess.PageFetcher;
import com.wia.model.preprocess.SubmitLogsParser;

/**
 * @author Saint Scott
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParserTest {

	private static Logger logger = Logger.getLogger(ParserTest.class.getName());
	private static String data = null;
	private static Set<Integer> set = null;
	private static String authorID = "wdp515105";
	private static Author author;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Fetcher fetcher = new PageFetcher();
		data = fetcher.fetch("http://acm.hdu.edu.cn/userstatus.php?user="
				+ authorID);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.AuthorInfoParser#parse(java.lang.String, Author)}
	 * .
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	// @Ignore
	@Test
	public void testAuthorInfoParser() throws ParseException, IOException {
		author = new Author(authorID);
		set = AuthorInfoParser.parse(data, author);
		Map<String, Author> map = AuthorListParser.parse(data,
				author.getAuthorID());
		assertEquals(map.size(), 6);
		logger.info(set.toString());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.SubmitLogsParser#parse(java.lang.String, java.util.List)}
	 * .
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	// @Ignore
	@Test
	// (timeout = 20000)
	public void testSubmitlogsParser() throws ParseException, IOException {
		List<SubmitLog> submitLogs = new ArrayList<>();
		Fetcher fetcher = new PageFetcher();
		for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
			int pid = iterator.next();
			data = fetcher.fetch("http://acm.hdu.edu.cn/status.php?user="
					+ authorID + "&pid=" + pid);
			while (true) {
				int first = SubmitLogsParser.parse(data, submitLogs);
				if (first > 0) {
					data = fetcher
							.fetch("http://acm.hdu.edu.cn/status.php?first="
									+ first + "&user=" + authorID + "&pid="
									+ pid);
				} else {
					break;
				}
			}
		}
		logger.info(author.toString());
		assertEquals(submitLogs.size(), author.getSubmissions());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.AuthorListParser#parse(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testAuthorListParser() throws ParseException, IOException {
		Fetcher fetcher = new PageFetcher();
		String content = fetcher.fetch("http://acm.hdu.edu.cn/ranklist.php");
		Map<String, Author> authorMap = AuthorListParser.parse(content,
				authorID);
		assertEquals(authorMap.size(), 25);
	}
}
