package launchBrowser;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LaunchBrowser {
    private static final Logger logger = Logger.getLogger(LaunchBrowser.class.getName());


	WebDriver driver;

	public static WebDriver openBrowser(String browserName) {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");          // Run in headless mode
        options.addArguments("--disable-gpu");       // Disable GPU (for Windows)
        options.addArguments("--window-size=1920,1080");

		if(browserName.equalsIgnoreCase(TestData.CHROME.name())) {
			logger.info(browserName.toUpperCase()+" Browser is opening...");
			return  new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase(TestData.FIREFOX.name())) {
			logger.info(browserName.toUpperCase()+" Browser is opening...");
			return  new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase(TestData.EDGE.name())) {
			logger.info(browserName.toUpperCase()+" Browser is opening...");
			return new EdgeDriver();
		}else {
			System.out.println("Please select correct browser");
			logger.warning(browserName.toUpperCase() + " Please provide correct browser name");
			return null;
		}
	}
	@Parameters({"browser"})

	@Test
	public void Test001(String br) throws InterruptedException {
	    driver = LaunchBrowser.openBrowser(br);

		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		Thread.sleep(5000);
		String title = driver.getTitle();
		if(title.contentEquals("OrangeHRM")) {
			System.out.println("Test Passed");
		}else {
			System.out.println("Test Failed");
		}
		driver.quit(); 	
	}

	
	
}
