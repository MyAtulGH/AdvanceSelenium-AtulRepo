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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class createCampaign {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		//importing property file for common data
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\CommonData_E18.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
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
		

	}

}
