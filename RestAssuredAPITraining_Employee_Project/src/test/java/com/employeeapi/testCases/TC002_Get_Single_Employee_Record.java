package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getAllEmployeeData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_Employee_Record ********");
	
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee" +empID);
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode();   //Getting Status Code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
	//	logger.info("************* Checking response Time **********");
		
		long responseTime=response.getTime();
		/*logger.info("Response time is ==> " + responseTime);
		
		if (responseTime>2000)
		{
			logger.warn("Response Time is greater than 2000");
		}*/
		Assert.assertTrue(responseTime<2000);
		
	}
	
	@Test
	void checkStatusLine()
	{
	//	logger.info("**********Checking Status Line**************");
		
		String statusLine = response.getStatusLine();     //Getting Status line
	//	logger.info("Status Line is== > " +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()	
	{
	//	logger.info("**********Checking Content Type**************");
		
		String contentType = response.header("Content-Type");
	//	logger.info("Content type is ==> " + contentType );
		Assert.assertEquals(contentType,"text/html;charset=UTF-8"  );
	}
	
	@Test
	void checkServerType()
	{
	//	logger.info("**********Checking Server Type**************");
		
		String serverType = response.header("Server");
	//	logger.info("Content type is ==> " + serverType );
		Assert.assertEquals(serverType,"nginx/1.16.0"  );
	}
	@Test
	void checkContentEncoding()
	{
	//	logger.info("**********Checking Content Encoding **************");
		
		String contentEncoding = response.header("Content-Encoding");
	//	logger.info("Content Encoding is ==> " + contentEncoding );
		Assert.assertEquals(contentEncoding,"gzip"  );
	}
	
	@Test
	void checkContentLength()
	{
	//	logger.info("**********Checking Status Line**************");
		
		String contentLength = response.header("Content-Length");
		
	/*	logger.info("Content Length is ==> " + contentLength );
		if (Integer.parseInt(contentLength)>100)
		{
		logger.warn(" Content Length is less than 100");	
		}*/
		Assert.assertTrue(Integer.parseInt(contentLength)<1500 );
	}
	
		
	@AfterClass
	void teaDown()
	{
		logger.info("********** Finished TC002_Get_Single_Employee_Record **************");
		
	}
	

}
