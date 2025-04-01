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

public class createContact_mamCopy {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\AdvanceSelenium_E18\\src\\test\\resources\\CommonData_E18.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);
		
		Random ran=new Random();
		int randomNum = ran.nextInt(1000);
		FileInputStream fis1=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
        Workbook wb = WorkbookFactory.create(fis1);
        String Campaign = wb.getSheet("DDT").getRow(1).getCell(2).getStringCellValue()+randomNum;
        String targetSize = wb.getSheet("DDT").getRow(1).getCell(3).getStringCellValue();
        String organization = wb.getSheet("Contact").getRow(1).getCell(2).getStringCellValue()+randomNum;
        String title = wb.getSheet("Contact").getRow(1).getCell(3).getStringCellValue();
        String contactName = wb.getSheet("Contact").getRow(1).getCell(4).getStringCellValue()+randomNum;
        String mobile = wb.getSheet("Contact").getRow(1).getCell(5).getStringCellValue();
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
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		contactLink.click();
		Thread.sleep(5000);
		WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(createContactBtn));
		createContactBtn.click();
		driver.findElement(By.name("organizationName")).sendKeys(organization);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactName);
		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
		
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String Window:allWindowIds)
		{
			driver.switchTo().window(Window);
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("selectCampaign"))
			{
				break;
			}
		}
		WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
         Select select1=new Select(selectTypeDD);
         select1.selectByValue("campaignName");
         driver.findElement(By.id("search-input")).sendKeys(Campaign);
         driver.findElement(By.xpath("//button[@class='select-btn']")).click();

         for(String Window:allWindowIds)
 		{
 			driver.switchTo().window(Window);
 			String actUrl = driver.getCurrentUrl();
 			if(actUrl.contains("create-contact"))
 			{
 				break;
 			}
 		}
         
         
        driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
          Thread.sleep(3000);
       String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contactName+" Successfully Added']")).getText();
        if(ConfirmationMsg.contains(contactName))
        {
        	System.out.println("Contact added Successfully");
        }
        else
        {
        	System.out.println("Contact not added");
        }
       Thread.sleep(5000);
       driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
       Actions action=new Actions(driver);
       action.moveToElement(logout).click().perform();
       driver.quit();

	}

}
