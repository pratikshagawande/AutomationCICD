package SoftwareTesting.AutomationProject.Test.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SoftwareTesting.AutomationProject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main/java//SoftwareTesting//AutomationProject//resources//GlobalData.properties");
		prop.load(fis);
		String BrowserName =System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String BrowserName = prop.getProperty("browser");
        System.out.println(BrowserName);
		if (BrowserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(BrowserName.contains("headless")) {
		        System.out.println("HeadlessAdded");
			options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {
			// firefox

		} else if (BrowserName.equalsIgnoreCase("Edge")) {

			driver = new EdgeDriver();
			System.setProperty("webdriver.edge.driver", "edge.exe");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonData(String FilePath) throws IOException {
		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// String to HashMap ->jackson databid
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String GetScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod
	public LandingPage LaunchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}

	@AfterMethod
	public void tearDow() {
		driver.close();
	}
}