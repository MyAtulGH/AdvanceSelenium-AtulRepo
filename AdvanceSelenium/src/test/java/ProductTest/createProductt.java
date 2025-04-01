package ProductTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class createProductt {

//	@Parameters("browser")
	@Test(groups= {"Integration"})
	public void createProductTest() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readingDataFromPropFile("browser");
//		String BROWSER = browser;
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("uname");
		String PWD = propUtil.readingDataFromPropFile("pwd");
		
		JavaUtility jutil=new JavaUtility();
		int randNum = jutil.getRandomNum(2000);
		ExcelFileUtility excelUtil=new ExcelFileUtility();
		String prodName = excelUtil.readingDataFromExcel("Product", 1, 2)+randNum;
//		String quantity = excelUtil.readingDataFromExcel("Product", 1, 3);
//		String price = excelUtil.readingDataFromExcel("Product", 1, 4);
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//navigating to ninza CRM
		driver.get(URL);
		//enter the username and password
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
		Thread.sleep(2000);
		//verification of dashboard
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(prodName);
		WebElement categatoryDD = driver.findElement(By.name("productCategory"));
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.select(categatoryDD, "Others");
//		driver.findElement(By.name("quantity")).sendKeys(quantity);
//		driver.findElement(By.name("price")).sendKeys(price);
		driver.findElement(By.name("quantity")).sendKeys("10");
		driver.findElement(By.name("price")).sendKeys("1000");
		WebElement vendorDD = driver.findElement(By.name("vendorId"));
		Wutil.select(vendorDD, "VID_015");
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(5000);
		String confMsg = driver.findElement(By.xpath("//div[text()='Product "+prodName+" Successfully Added']")).getText();
/*
		if(confMsg.contains(prodName))
		{
			Reporter.log("Product " + prodName + "  added successfully", true);
		}
		else
		{
			Reporter.log("Product not added", true);
		}
*/
		boolean status = confMsg.contains(prodName);
//		Assert.assertEquals(status, true, "Product not added");
		Assert.assertTrue(status, "Product not added");
		Reporter.log("Contact "+ prodName + " added successfully", true);
		
		Thread.sleep(2000);
		//logout
		DashboardPage dp=new DashboardPage(driver);
		dp.logout();
        //close the browser
        driver.quit();

	}

}
