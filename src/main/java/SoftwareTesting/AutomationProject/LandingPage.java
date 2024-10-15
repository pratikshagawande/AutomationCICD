package SoftwareTesting.AutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SoftwareTesting.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement UserName;
	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Submit;

	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errormessage;

	public ProductCataloguePage LoginApplication(String username, String password) {
		UserName.sendKeys(username);
		Password.sendKeys(password);
		Submit.click();
		ProductCataloguePage productcataloguepage = new ProductCataloguePage(driver);
		return productcataloguepage;
	}

	public String getErrorMessage() {
		waitforElementsToDisappear2(errormessage);
		return errormessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
