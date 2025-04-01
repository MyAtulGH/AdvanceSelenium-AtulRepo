package DataDrivenTest;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FillForm {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(1000);
	
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys("mish");
		driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys("111");
	/*	driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		WebElement link = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions action = new Actions(driver);
		action.moveToElement(link).click().perform();
		
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
*/
	}

}
