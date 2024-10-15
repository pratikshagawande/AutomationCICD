package SoftwareTesting.AutomationProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SoftwareTesting.AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By productList = By.cssSelector(".col-lg-4");
	By addtocart = By.xpath("//button[@class='btn w-10 rounded']");

	By ToastMessage = By.id("toast-container");

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		waitforElementsToAppear(productList);
		return products;
	}

	public WebElement getProductByName(String ProductName) {

		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(ProductName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String ProductName) {

		WebElement prod = getProductByName(ProductName);
		prod.findElement(addtocart).click();
		waitforElementsToDisappear2(ToastMessage);
		waitforElementsToDisappear(spinner);
	}

}
