package testngTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import launchBrowser.LaunchBrowser;

public class OrangeHRM {
	WebDriver driver;
	
	@Test(priority=1)
	public void launchApp() {
		driver = LaunchBrowser.openBrowser("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
	@Test(priority=2)
	public void verifyLogo() throws InterruptedException {
		Thread.sleep(5000);
		boolean is_displayed = driver.findElement(By.xpath("//img[@alt=\"company-branding\"]")).isDisplayed();
		System.out.println(is_displayed);
		
		
	}
	@Test(priority=3)
	public void loginApp() throws InterruptedException {
		
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
	}
	@Test(priority=4)
	public void closeApp() {
		driver.quit();
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
