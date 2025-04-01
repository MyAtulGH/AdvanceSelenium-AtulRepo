package genericBaseClassUtility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass 
{
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Established database connection");
	
	}

	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Pre configuration setup");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Launch the browser");
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Login");
	}
	
	@AfterMethod
	public void aftereMethod()
	{
		System.out.println("Logout");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("Close the browser");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("Post configuration setup");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("Closed the db connection");
	}
	
}
