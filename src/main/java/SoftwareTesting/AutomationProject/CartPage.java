package SoftwareTesting.AutomationProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SoftwareTesting.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> ProductTitle;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutclick;

	public Boolean VerifyProductDisplay(String ProductName) {
		List<WebElement> cartProducts = ProductTitle;
		Boolean match = ProductTitle.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ProductName));

		return match;

	}

	public checkOutPage Checkoutpage() {
		checkoutclick.click();
		checkOutPage checkoutpage = new checkOutPage(driver);
		return checkoutpage;

	}

}
