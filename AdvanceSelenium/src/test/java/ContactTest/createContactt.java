package ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import GenericUtility.ActionUtility;

public class createContactt {

//	@Parameters("browser")
	@Test(groups={"Regression"})
	public void createContactTest() throws IOException, InterruptedException 
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
		String organization = exUtil.readingDataFromExcel("Contact", 1, 2)+randomNum;
		String title = exUtil.readingDataFromExcel("Contact", 1, 3);
		String contactName = exUtil.readingDataFromExcel("Contact", 1, 4)+randomNum;
//		String mobile = exUtil.readingDataFromExcel("Contact", 1, 5);
        
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome")
				)
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
		driver.get(URL);
//		driver.findElement(By.id("username")).sendKeys(UN);
//		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
//		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
		
		Thread.sleep(2000);
		
/*		
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
//		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.name("targetSize")).sendKeys("100");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
*/
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
		ccp.createCampaignWithmandatoryFields(Campaign, "100");
		
		Thread.sleep(3000);
//		WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		WebElement contactLink = dp.getContactsLink();
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.waitForElementToBeClickable(driver, contactLink,20);
		contactLink.click();
		Thread.sleep(5000);

//		WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
		ContactsPage ccp1=new ContactsPage(driver);
		WebElement createContactBtn = ccp1.getCreateContactBtn();
		Wutil.waitForElementToBeClickable(driver, createContactBtn,20);
		createContactBtn.click();
/*		
		driver.findElement(By.name("organizationName")).sendKeys(organization);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactName);
//		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.name("mobile")).sendKeys("987654321");
		driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
*/
		CreateContactPage cct=new CreateContactPage(driver);
		cct.createContactWithCampaign(organization, title, contactName, "987654321", "selectCampaign", "create-contact", Campaign);
		
		 Thread.sleep(5000);
/*		
		Wutil.switchToWindow(driver, "selectCampaign");
		WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
         Wutil.select(selectTypeDD, "campaignName");
         driver.findElement(By.id("search-input")).sendKeys(Campaign);
         driver.findElement(By.xpath("//button[@class='select-btn']")).click();

         Wutil.switchToWindow(driver, "create-contact");
         
        driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
          Thread.sleep(3000);
*/
//       String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contactName+" Successfully Added']")).getText();		
        String ConfirmationMsg = ccp1.getConfMsg().getText(); 
 /*      
       if(ConfirmationMsg.contains(contactName))
        {
        	Reporter.log("Contact added Successfully", true);
        }
        else
        {
        	Reporter.log("Contact not added", true);
        }
*/
        boolean status = ConfirmationMsg.contains(contactName);
		Assert.assertEquals(status, true, "Contact not added");
//		Assert.assertTrue(status, "Campaign not added");
		Reporter.log("Contact "+ contactName + " added successfully", true);
		
       Thread.sleep(5000);
/*       
       driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
//       Actions action=new Actions(driver);
//       action.moveToElement(logout).click().perform();
       ActionUtility Autil=new ActionUtility();
       Autil.simpleClick(driver, logout);
*/
       dp.logout();
       driver.quit();
		

	}

}
