package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase
{
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("*********Started TC001_Get_All_Employees ********");
	
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("***** Checking  response body *********");
		
		String responseBody =response.getBody().asString();
		logger.info("Response Body ==> "+ responseBody);
		Assert.assertTrue(responseBody!=null);
	
//Response contains responseBody,time,status code ,status line etc but here we need only responseBody ,so we used response.getBody().asString()
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("******** Checking Status Code *********");
		
		int statusCode = response.getStatusCode(); //Getting status code
		logger.info("Status Code is ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("************* Checking response Time **********");
		
		long responseTime=response.getTime();
		logger.info("Response time is ==> " + responseTime);
		
		if (responseTime>2000)
		{
			logger.warn("Response Time is greater than 2000");
		}
		Assert.assertTrue(responseTime<2000);
		
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("**********Checking Status Line**************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is== > " +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()	
	{
		logger.info("**********Checking Content Type**************");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==> " + contentType );
		Assert.assertEquals(contentType,"application/json;charset=utf-8"  );
	}
	
	@Test
	void checkServerType()
	{
		logger.info("**********Checking Server Type**************");
		
		String serverType = response.header("Server");
		logger.info("Content type is ==> " + serverType );
		Assert.assertEquals(serverType,"nginx/1.16.0"  );
	}
	@Test
	void checkContentEncoding()
	{
		logger.info("**********Checking Content Encoding **************");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==> " + contentEncoding );
		Assert.assertEquals(contentEncoding,"gzip"  );
	}
	
	@Test
	void checkContentLength()
	{
		logger.info("**********Checking Status Line**************");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==> " + contentLength );
		if (Integer.parseInt(contentLength)>100)
		{
		logger.warn(" Content Length is less than 100");	
		}
		Assert.assertTrue(Integer.parseInt(contentLength)>100 );
	}
	
	@Test
	void checkTestCookies()
	{
		logger.info("**********Checking Cookies **************");
		
		String cookie = response.getCookie("PHPSESSID");

//Assert.assertEquals(cookie,"1esuvsfslcmiee2bfrsgnijtg0"  );
	}
	
	@AfterClass
	void teaDown()
	{
		logger.info("********** Finished TC001_Get_All_Employees **************");
		
	}
	
}
