package SoftwareTesting.AutomationProject.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import SoftwareTesting.AbstractComponents.AbstractComponents;
import SoftwareTesting.AutomationProject.CartPage;
import SoftwareTesting.AutomationProject.ConfirmationPage;
import SoftwareTesting.AutomationProject.LandingPage;
import SoftwareTesting.AutomationProject.ProductCataloguePage;
import SoftwareTesting.AutomationProject.checkOutPage;
import SoftwareTesting.AutomationProject.Test.TestComponents.BaseTest;
import SoftwareTesting.AutomationProject.Test.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException {
		String ProductName = "ZARA COAT 3";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		landingpage.LoginApplication("adviky20222112@gmail.com", "Password@12345");

		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() {
		ProductCataloguePage productcataloguepage = landingpage.LoginApplication("pratiksha148@gmail.com",
				"Advik@1234");

		List<WebElement> products = productcataloguepage.getProductList();
		productcataloguepage.addProductToCart("ZARA COAT 3");
		CartPage cartpage = productcataloguepage.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}
}
