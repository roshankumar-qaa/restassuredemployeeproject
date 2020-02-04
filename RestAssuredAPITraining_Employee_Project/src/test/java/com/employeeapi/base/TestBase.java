package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase
{
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="18"; //Hard coded-input for Get details of Single  Employee & update employee
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		logger=Logger.getLogger("EmployeeRestAPI"); //added logger
		PropertyConfigurator.configure("log4j.properties"); //added Logger
		logger.setLevel(Level.DEBUG);
	}
	
	
}
