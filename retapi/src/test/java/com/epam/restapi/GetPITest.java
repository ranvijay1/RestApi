package com.epam.restapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.empam.pojomapper.Data;
import com.empam.pojomapper.UserDetails;
import com.epam.base.Base;
import com.epam.httpclient.HttpClientConnection;
import com.epam.jacsonutils.JacsonUtils;



public class GetPITest {
	String uri;
	HttpClientConnection restClient;
	CloseableHttpResponse closeableResponse;
	String responseString = "";
	Properties prop;

	@BeforeMethod
	public void setUp() {
		prop = Base.getUserProperty();
	}
	
	@Test (priority = 1)
	public void getAllUserTest() {
		uri = prop.getProperty("URL")  + prop.getProperty("ENDUSERURL");
		restClient = new HttpClientConnection();
		closeableResponse = restClient.get(uri);
		int statusCode = closeableResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, Base.Res_code_200, "Not getting valid response");
		
		
		// Getting Json response
		try {
			responseString = EntityUtils.toString(closeableResponse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject responseJson = new JSONObject(responseString);
		JSONArray  jsonArray = responseJson.getJSONArray("data");
		System.out.println("Length of data --> " + jsonArray.length());
		Assert.assertEquals(true, jsonArray.length() <= 3, "Data length is not less than  equal to 3");
		
		UserDetails userdetail = JacsonUtils.convertJsonToJavaObject(responseJson.toString(), UserDetails.class);
		
		Assert.assertEquals("2", userdetail.getPage(), "Page value is not 2");
		Assert.assertEquals("12", userdetail.getTotal(), "Total is not 12");
		Data allData[] = userdetail.getData();
		for(Data data : allData) {
			if(data.getId().equals("5")) {
				Assert.assertEquals("Charles", data.getFirst_name());
				System.out.println("First Name -- > " + data.getFirst_name());
			}
		}
		
		
		// Getting All Headers
		Header[] allHeaders = closeableResponse.getAllHeaders();
		HashMap<String, String> hm = new HashMap<String, String>();
		for(Header header : allHeaders) {
			hm.put(header.getName(), header.getValue());
		}
		
		System.out.println("All Headers---->" + hm);
	}
	
	@Test (priority = 2)
	public void getSingleUserNotFound() {
		uri = prop.getProperty("URL") + prop.getProperty("SINGLEUSERNOTFOUND");
		restClient = new HttpClientConnection();
		closeableResponse = restClient.get(uri);
		
		Assert.assertEquals(Base.Res_code_404, closeableResponse.getStatusLine().getStatusCode(), "Stattus code is not 404");
		
		try {
			responseString = EntityUtils.toString(closeableResponse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("{}", responseString, "Json object is not empty");
		System.out.println("String ---> " +responseString);
		
	
		
	}
}
