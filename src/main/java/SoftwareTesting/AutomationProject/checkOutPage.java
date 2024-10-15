package SoftwareTesting.AutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SoftwareTesting.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {
	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = " //section[@class='ta-results list-group ng-star-inserted']/button[2]")
	WebElement selectcountry;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement SubMit;

	By CountryList = By.xpath(" //section[@class='ta-results list-group ng-star-inserted']/button[2]");

	public void selectCountry() {
		Actions a = new Actions(driver);
		a.sendKeys(country, "India").build().perform();

		waitforElementsToAppear(CountryList);
		selectcountry.click();
	}

	public ConfirmationPage SubmitOrder() {
		SubMit.click();

		return new ConfirmationPage(driver);
	}

}
