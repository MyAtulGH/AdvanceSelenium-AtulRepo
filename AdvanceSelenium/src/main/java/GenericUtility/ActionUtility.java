package GenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility 
{
	public void isElementClickable(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
	
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void simpleClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.click(element).perform();
	}

}
