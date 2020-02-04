package com.employeeapi.testCases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {

	RequestSpecification httprequest;
	Response response;
	
	String empName=RestUtils.empAge();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("*********Started TC005_Delete_Employee_Record ********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET ,"/employees");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Capture id
		String empID = jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/" + empID);
		
				 
		 Thread.sleep(5000);
	}

	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains("Successfully deleted Records"),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
	}

	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode();  //Getting StatusCode
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine();  //Getting StatusLine
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()	
	{
		String contentType = response.header("Content-Type");

		Assert.assertEquals(contentType,"application/json;charset=utf-8"  );
	}
	
	@Test
	void checkServerType()
	{
		String serverType = response.header("Server");

		Assert.assertEquals(serverType,"nginx/1.16.0"  );
	}
	
	@AfterClass
	void teaDown()
	{
		logger.info("********** Finished TC005_Delete_Employee_Record **************");
		
	}

}
