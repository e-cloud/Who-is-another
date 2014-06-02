package com.wia.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Submit {
	public static CloseableHttpClient myClient = HttpClients.createDefault();

	public static String curProblemid = null;

	private static Map<String, String> languages = null;

	public static void initLanguages() {
		if (languages == null) {
			languages = new HashMap<>();
			languages.put("G++", "0");
			languages.put("GCC", "1");
			languages.put("C++", "2");
			languages.put("C", "3");
			languages.put("Pascal", "4");
			languages.put("Java", "5");
		}
	}

	public static String getProblemUrl(String Problemid) {
		curProblemid = Problemid;
		String url = "http://acm.hdu.edu.cn/showproblem.php?pid=";
		return url + Problemid;
	}

	public static void login(String username, String userpass) {
		initLanguages();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("userpass", userpass));
		HttpPost myPost = new HttpPost(
				"http://acm.hdu.edu.cn/userloginex.php?action=login&cid=0&notice=0");
		try {
			myPost.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CloseableHttpResponse myRes = myClient.execute(myPost);

			HttpGet myGet = new HttpGet(
					"http://acm.hdu.edu.cn/submit.php?pid=1006");
			myClient.execute(myGet);
			myRes.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void submintCode(String language, String usercode) {

		List<NameValuePair> submitForm = new ArrayList<NameValuePair>();
		submitForm.add(new BasicNameValuePair("check", "0"));
		submitForm.add(new BasicNameValuePair("problemid", curProblemid));
		submitForm.add(new BasicNameValuePair("language", languages
				.get(language)));
		submitForm.add(new BasicNameValuePair("usercode", usercode));
		HttpPost submitPost = new HttpPost(
				"http://acm.hdu.edu.cn/submit.php?action=submit");

		try {
			submitPost.setEntity(new UrlEncodedFormEntity(submitForm));
			CloseableHttpResponse myRes = myClient.execute(submitPost);
			submitPost.releaseConnection();
			myRes.close();
			System.out.println("Done!");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}