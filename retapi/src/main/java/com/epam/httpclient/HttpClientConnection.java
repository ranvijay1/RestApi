package com.epam.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientConnection {

	public CloseableHttpResponse get(String url) {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse closeableResponse = null;
		try {
			closeableResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return closeableResponse;
	}
	
	public CloseableHttpResponse postMethod(String Url, String payload) {
		System.out.println(payload);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(Url);
		post.addHeader("Content-Type", "application/json");
		try {
			post.setEntity(new StringEntity(payload));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);
		return response;
	}

}
