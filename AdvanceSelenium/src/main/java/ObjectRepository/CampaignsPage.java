package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage 
{
	WebDriver driver;
	
	public CampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//select[@class='form-control']")
	private WebElement searchByDD;
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement searchField;
	
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement createCampaignBtn;

	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;
	
	

	public WebElement getConfMsg() {
		return confMsg;
	}

	public WebElement getSearcByDD() {
		return searchByDD;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

}
