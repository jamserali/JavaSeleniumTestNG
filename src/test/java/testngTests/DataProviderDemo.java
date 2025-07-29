package testngTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import launchBrowser.LaunchBrowser;


@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class DataProviderDemo {
	
	WebDriver driver;
	
	
	@Test(description = "Verify valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Login test with valid credentials")
    @Story("Valid Login")
	@Parameters({"browser"})
	@BeforeClass
	public void launchApp(String br) {
		driver = LaunchBrowser.openBrowser(br);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
	@Test(dataProvider="dp")
	public void loginApp(String username,String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//*[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		Thread.sleep(2000); // Optional short wait before checking for elements

		// Try to check if dashboard is present using wait
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Dashboard']")));

			// Dashboard is present → Login success
			driver.findElement(By.xpath("//*[@class='oxd-userdropdown-tab']")).click();
			driver.findElement(By.xpath("//a[text()='Logout']")).click();

			Assert.assertTrue(true);
		} catch (Exception e) {
			// Dashboard not found → Login failed, check for error message
			String errorMsg = driver.findElement(By.xpath("//*[text()='Invalid credentials']")).getText();
			Assert.assertEquals(errorMsg, "Invalid credentials");
		}
	}

	@AfterClass
	public void closeApp() throws InterruptedException {

		driver.quit();
		
	}
	
	@DataProvider(name="dp")
	public Object[][] dataProvider1() {
		Object data [][] = {
				{"Admin","admin123"},
				{"John","admin123"},
				{"Admin","testing123"},
				{"smith","smith123"},		
		};
		return data;
		
	}
	

}
