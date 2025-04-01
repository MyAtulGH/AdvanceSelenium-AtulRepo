package DataDrivenTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderUsage 
{
	@Test(dataProvider = "getData")
	public void testCase(String firstName,String lastName)
	{
		System.out.println("FirstName: "+firstName+" Lastname: "+lastName);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr = new Object[3][2];
		objArr[0][0] = "John";
		objArr[0][1] = "dave";
		objArr[1][0] = "Annie";
		objArr[1][1] = "ravan";
		objArr[2][0] = "Smith";
		objArr[2][1] = "louise";
		
		return objArr;
		
	}
	

}
