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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Submit {
	private final CloseableHttpClient httpClient;
	public static String currentProblemID = null;

	private static Map<String, String> languages = null;

	public Submit() {
		httpClient = HttpClients.createDefault();
		initLanguages();
	}

	private static void initLanguages() {
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

	public static String getProblemUrl(String problemID) {
		currentProblemID = problemID;
		String url = "http://acm.hdu.edu.cn/showproblem.php?pid=";
		return url + problemID;
	}

	public boolean login(String username, String password) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("userpass", password));
		HttpPost myPost = new HttpPost(
				"http://acm.hdu.edu.cn/userloginex.php?action=login&cid=0&notice=0");
		try {
			myPost.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CloseableHttpResponse myRes = httpClient.execute(myPost);
			String res = EntityUtils.toString(myRes.getEntity());
			if (res.contains("No such user or wrong password")) {
				myRes.close();
				return false;
			}
			myRes.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void submitCode(String language, String usercode) {

		List<NameValuePair> submitForm = new ArrayList<NameValuePair>();
		submitForm.add(new BasicNameValuePair("check", "0"));
		submitForm.add(new BasicNameValuePair("problemid", currentProblemID));
		submitForm.add(new BasicNameValuePair("language", languages
				.get(language)));
		submitForm.add(new BasicNameValuePair("usercode", usercode));
		HttpPost submitPost = new HttpPost(
				"http://acm.hdu.edu.cn/submit.php?action=submit");

		try {
			submitPost.setEntity(new UrlEncodedFormEntity(submitForm));
			CloseableHttpResponse myRes = httpClient.execute(submitPost);

			System.out.println("Done!");
			myRes.close();
			httpClient.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}