package CampaignTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class createCampaignn {

//	@Parameters("browser")
//	@Test(groups= {"Smoke Test"})
//	@Test(priority = 1)
//	@Test(invocationCount = 0)
//	@Test(invocationCount = 4, threadPoolSize = 2)
//	@Test(enabled = false)
	@Test()
	public void createCampaignTest() throws IOException, InterruptedException
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readingDataFromPropFile("browser");
//		String BROWSER = browser;
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("uname");
		String PWD = propUtil.readingDataFromPropFile("pwd");
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("DDT", 1, 2)+randomNum;
//		String targetSize = exUtil.readingDataFromExcel("DDT", 1, 3);
		
		String closeDate = jUtil.generateReqDate(30);
		
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

		/*		//enter the username and password
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		//click on sign in button
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).clear();
//		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.name("targetSize")).sendKeys("100");
		driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
*/
		
		//above commented statements using Object Repository
		LoginPage lp = new LoginPage(driver);
		lp.login(UN, PWD);
		DashboardPage dp = new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
//		ccp.createCampaignWithmandatoryFields(Campaign, "100");
		ccp.createCampaignWithCloseDate(Campaign, "100", closeDate);
		
		Thread.sleep(2000);
//		String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		String ConfMsg = cp.getConfMsg().getText();
		
		boolean status = ConfMsg.contains(Campaign);
		Assert.assertEquals(status, true, "Campaign not added");
//		Assert.assertTrue(status, "Campaign not added");
		Reporter.log("Campaign "+ Campaign + " added successfully", true);
		
/*		
 		if(ConfMsg.contains(Campaign))
		{
			Reporter.log("Campaign " + Campaign + " added successfully", true);
		}
		else
		{
			Reporter.log("Campaign not added", true);
		}
*/
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
        WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
        Actions action=new Actions(driver);
        action.moveToElement(logoutBtn).click().perform();
        //close the browser
        driver.quit();

	}
	
//	@Parameters("browser")
//	@Test(groups= {"Regression"})
//	@Test(priority = 2)
	@Test(dependsOnMethods = "createCampaignTest")
	public void createCampaignWithDateTest() throws InterruptedException, IOException 
	{
		//importing property file for common data
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\CommonData_E18.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
//		String BROWSER = browser;
		String URL = prop.getProperty("url");
		String UNAME = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (BROWSER.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(1000);
		
		//random number for new Campaign names each time we execute
		Random ran = new Random();
		int randomNum = ran.nextInt(1000);
		
		//importing excel file for test script data
		FileInputStream fis1 = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String Campaign = wb.getSheet("DDT").getRow(1).getCell(2).getStringCellValue()+randomNum;
//		String TargetSize = wb.getSheet("DDT").getRow(1).getCell(3).getStringCellValue();

		
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys(Campaign);
		driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys("100");
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//getting current date
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-YYYY");
		String todaysDate = sim.format(dateObj);
		System.out.println(todaysDate);
		
		//playing with current date date
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String closeDate = sim.format(cal.getTime());
		System.out.println(closeDate);
		
		driver.findElement(By.name("expectedCloseDate")).sendKeys(todaysDate);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		CampaignsPage cp = new CampaignsPage(driver);
		String ConfMsg = cp.getConfMsg().getText();
		
		boolean status = ConfMsg.contains(Campaign);
		Assert.assertEquals(status, false, "Campaign not added");
//		Assert.assertTrue(status, "Campaign not added");
		Reporter.log("Campaign "+ Campaign + " added successfully", true);
/*		
		if(ConfMsg.contains(Campaign))
		{
			Reporter.log("Campaign " + Campaign + " added successfully", true);
		}
		else
		{
			Reporter.log("Campaign not added", true);
		}
*/		
/*
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
        WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
        Actions action=new Actions(driver);
        action.moveToElement(logoutBtn).click().perform();
*/      
        //close the browser
		driver.quit();
		
	}

}
