package Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class loginn {

//	@Parameters("browser")
	@Test(groups= {"Smoke Test"})
//	public void loginTest(String browser) throws InterruptedException, IOException 
	public void loginTest() throws InterruptedException, IOException 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\OneDrive\\Desktop\\AdvanceJavaSelenium\\CommonData_E18.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
//		String BROWSER = browser;
		System.out.println("Browser : "+BROWSER);
		String URL = prop.getProperty("url");
		String UNAME = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		
		WebDriver driver = null;
		
		ChromeOptions Coption = new ChromeOptions();
		Coption.addArguments("--headless");
		FirefoxOptions Foption = new FirefoxOptions();
		Foption.addArguments("--headless");
		EdgeOptions Eoption = new EdgeOptions();
		Eoption.addArguments("--headless");
		
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
		
		//finding web elements using traditional approach
//		driver.findElement(By.id("username")).sendKeys(UNAME);
//		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
//		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//finding web elements using pom object repository approach
		LoginPage lp = new LoginPage(driver);
		//using object repository objects to login
//		lp.getUsernameField().sendKeys(UNAME);
//		lp.getPasswordField().sendKeys(PWD);
//		lp.getSignInBtn().click();
		
		//using object repository method to login
		lp.login(UNAME, PWD);
		
		Thread.sleep(2000);
		
		//verification of dashboard
		String expectedURL="http://49.249.28.218:8098/dashboard";
		String actualURL=driver.getCurrentUrl();
/*		if(actualURL.equals(expectedURL))
		{
			Reporter.log("Validation is pass", true);
		}
		else
		{
			Reporter.log("validation is failed", true);
		}
*/
		Assert.assertEquals(actualURL, expectedURL,"**Validation is failed**");
		//logout
		DashboardPage dp=new DashboardPage(driver);
		dp.logout();
        //close the browser
        driver.quit();
        
	}		
		

}
