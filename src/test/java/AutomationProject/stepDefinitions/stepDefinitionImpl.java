package AutomationProject.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SoftwareTesting.AutomationProject.CartPage;
import SoftwareTesting.AutomationProject.ConfirmationPage;
import SoftwareTesting.AutomationProject.LandingPage;
import SoftwareTesting.AutomationProject.ProductCataloguePage;
import SoftwareTesting.AutomationProject.checkOutPage;
import SoftwareTesting.AutomationProject.Test.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	public LandingPage landingpage; 
	public ProductCataloguePage productcataloguepage;
	
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
	  
		landingpage=LaunchApplication();
	}

	
	@Given("I logged in with username {string} with password {string}")
	//@Given("Logged in with usernamepratiksha1488@gmail.com and passwordPassword@1234")
	public void i_logged_in_with_username_with_password(String name,String pass )  throws IOException {
		productcataloguepage = landingpage.LoginApplication(name,pass);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productcataloguepage.getProductList();
		productcataloguepage.addProductToCart(productName);
	  
	   
	}

	@When("^checkout (.+) and submit the order $")
	//@When("checkout ZARA COAT {int} and submit the order")
	public void checkout_and_submit_the_order(String productName) {
	   
		CartPage cartpage = productcataloguepage.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage checkoutpage = cartpage.Checkoutpage();

		checkoutpage.selectCountry();
		ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();
	}

	@Then("{string} is displayed on ConfirmationPage")
	public void is_displayed_on_c_onfirmation_page(String string) {
	 
		String ConfirmationMessage = confirmationPage.getconfirmMessage();

		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(string));
	}




}
