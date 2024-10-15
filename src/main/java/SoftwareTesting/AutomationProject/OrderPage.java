package SoftwareTesting.AutomationProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SoftwareTesting.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderList;

	
	public Boolean VerifyOrderDisplay(String ProductName) {
		List<WebElement> cartProducts = orderList;
		Boolean match = orderList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ProductName));

		return match;
	}

}
