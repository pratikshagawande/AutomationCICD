package SoftwareTesting.AutomationProject.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SoftwareTesting.AbstractComponents.AbstractComponents;
import SoftwareTesting.AutomationProject.CartPage;
import SoftwareTesting.AutomationProject.ConfirmationPage;
import SoftwareTesting.AutomationProject.LandingPage;
import SoftwareTesting.AutomationProject.OrderPage;
import SoftwareTesting.AutomationProject.ProductCataloguePage;
import SoftwareTesting.AutomationProject.checkOutPage;
import SoftwareTesting.AutomationProject.Test.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest extends BaseTest {
	WebDriver driver;
	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "getData",groups= {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ProductCataloguePage productcataloguepage = landingpage.LoginApplication(input.get("email"),
				input.get("password"));

		List<WebElement> products = productcataloguepage.getProductList();
		productcataloguepage.addProductToCart(input.get("ProductName"));
		CartPage cartpage = productcataloguepage.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		checkOutPage checkoutpage = cartpage.Checkoutpage();

		checkoutpage.selectCountry();
		ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();

		String ConfirmationMessage = confirmationPage.getconfirmMessage();

		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	// to verify Zara 3 product display in order
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCataloguePage productcataloguepage = landingpage.LoginApplication("pratiksha1488@gmail.com",
				"Password@1234");

		OrderPage orderpage = productcataloguepage.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(ProductName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") +"\\src\\test\\java\\SoftwareTesting\\AutomationProject\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	}
