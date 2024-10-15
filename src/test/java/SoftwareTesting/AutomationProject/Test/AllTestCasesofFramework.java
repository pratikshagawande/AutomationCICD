package SoftwareTesting.AutomationProject.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllTestCasesofFramework {
	public static void main(String[] args) throws InterruptedException {

		String password = "Password@1234";
		String emailId = "adviky2022211@gmail.com";
		String ProductName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(emailId);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(ProductName))
				.findFirst().orElse(null);
		prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		driver.findElement(By.xpath(" //section[@class='ta-results list-group ng-star-inserted']/button[2]")).click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

		String Confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(Confirmationmessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		//to verify Zara 3 product display in order
		
		
	}
}


