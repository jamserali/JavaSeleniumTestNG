package testngTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyMethods {

	@Test(priority=1,groups= {"regression","functional"})
	public void openApp() {
		
		Assert.assertTrue(true);
	}
	
//	@Test(priority=2,dependsOnMethods= {"openApp"},groups= {"sanity"})
	@Test(priority=2,groups= {"sanity"})

	public void login() {
		Assert.assertEquals(123,123);

	}
//	@Test(priority=3,dependsOnMethods= {"login"},groups= {"regression"})
	@Test(priority=3,groups= {"regression"})

	public void search() {
		Assert.assertTrue(true);

	}
	@Test(priority=4,groups= {"sanity"})

	public void Advancedsearch() {
		
		Assert.assertTrue(true);

	}
	@Test(priority=5,groups= {"regression"})

	public void closeApp() {
		Assert.assertTrue(true);

	}
	
	
	
	
	
}
