package com.epam.restapi;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.empam.pojomapper.CreatedUser;
import com.empam.pojomapper.NewUserRegistration;
import com.empam.pojomapper.TokenDetail;
import com.empam.pojomapper.User;
import com.epam.base.Base;
import com.epam.httpclient.HttpClientConnection;
import com.epam.jacsonutils.JacsonUtils;



public class PostAPITest {
	
	Properties prop = null;
	String uri;
	HttpClientConnection httpClient;
	CloseableHttpResponse  closableResopnse;
	String responseString = null;
	
	
	@BeforeMethod
	public void setup() {
		prop = Base.getUserProperty();
		
	}
	
	@Test (priority = 1)
	public void registerUser() {
		uri = prop.getProperty("URL") + prop.getProperty("REGISTER");
		String email = prop.getProperty("email");
		String password = prop.getProperty("password");
		
		NewUserRegistration newUser = new NewUserRegistration(email, password);
		
		String objectToJSonString = JacsonUtils.convertPojoToJson(newUser);

		httpClient = new HttpClientConnection();
		closableResopnse = httpClient.postMethod(uri, objectToJSonString);
		
		// Verifying user created successfully
		Assert.assertEquals(Base.Res_code_201, closableResopnse.getStatusLine().getStatusCode(), "User is not created successfully");
		
		System.out.println("Status Code = " + closableResopnse.getStatusLine().getStatusCode());
		
		try {
			responseString = EntityUtils.toString(closableResopnse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TokenDetail tokenObj = JacsonUtils.convertJsonToJavaObject(responseString, TokenDetail.class);
		
		Assert.assertEquals("QpwL5tke4Pnpja7X", tokenObj.getToken());
		
		System.out.println("Token is = " + tokenObj.getToken());
		
		
	}
	
	@Test (priority = 2)
	public void createUser() {
		uri = prop.getProperty("URL") + prop.getProperty("CREATEUSER");
		String name = prop.getProperty("name");
		String job = prop.getProperty("job");
		User user = new User(name, job);
		String jsonString = JacsonUtils.convertPojoToJson(user);
		closableResopnse = httpClient.postMethod(uri, jsonString);
		System.out.println("Status Code = " + closableResopnse.getStatusLine().getStatusCode());
		
		Assert.assertEquals(Base.Res_code_201, closableResopnse.getStatusLine().getStatusCode());

		try {
			responseString = EntityUtils.toString(closableResopnse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("String " + responseString);
		CreatedUser createdUser = JacsonUtils.convertJsonToJavaObject(responseString, CreatedUser.class);
		
		Assert.assertEquals(name, createdUser.getName(), "Name is not as per created");
		Assert.assertEquals(job, createdUser.getJob(), "Job is not as per created");
		
		
		
		
	}

}
