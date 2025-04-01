package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class CreateContactPage 
{
	WebDriver driver;
	
	
	public CreateContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="organizationName")
	private WebElement orgName;
	
	@FindBy(name="title")
	private WebElement titleField;
	
	@FindBy(name="contactName")
	private WebElement contName;
	
	@FindBy(name="mobile")
	private WebElement mobField;
	
	@FindBy(xpath="(//*[name()='svg' and @role='img'])[2]")
	private WebElement selectCampBtn;
	
	@FindBy(id="search-criteria")
	private WebElement searchDD;
	
	@FindBy(id="search-input")
	private WebElement seachInp;
	
	@FindBy(xpath="//button[@class='select-btn']")
	private WebElement selectBtn;
	
	@FindBy(xpath="//button[text()='Create Contact']")
	private WebElement createContBtn;


	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getTitleField() {
		return titleField;
	}

	public WebElement getContName() {
		return contName;
	}

	public WebElement getMobField() {
		return mobField;
	}

	public WebElement getSelectCampBtn() {
		return selectCampBtn;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSeachInp() {
		return seachInp;
	}

	public WebElement getSelectBtn() {
		return selectBtn;
	}

	public WebElement getCreateContBtn() {
		return createContBtn;
	}
	
	
	public void createContactWithCampaign(String org,String title,String cont,String mob,String childUrl,String parentUrl,String campName)
	{
		orgName.sendKeys(org);
		titleField.sendKeys(title);
		contName.sendKeys(cont);
		mobField.sendKeys(mob);
		selectCampBtn.click();
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.switchToWindow(driver, childUrl);
		wUtil.select(searchDD, 1);
		seachInp.sendKeys(campName);
		selectBtn.click();
		wUtil.switchToWindow(driver, parentUrl);
		createContBtn.click();
	}

}
