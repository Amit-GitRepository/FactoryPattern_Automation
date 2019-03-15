package com.xassure.ui.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.xassure.api.models.users.UsersParent;
import com.xassure.api.services.UsersService;
import com.xassure.ui.pages.CheckoutPage;
import com.xassure.ui.pages.HomePage;
import com.xassure.ui.pages.ProductsPage;
import com.xassure.ui.utils.ConfigPropertyReader;

public class TestBeachbodyWorkflow extends BaseTest{
	
	HomePage home;
	ProductsPage productsPage;
	CheckoutPage checkoutPage;
	
	String firstName, lastName, StreetAddress, OptionalAddress, City, State, PostalCode, Telephone;
	
	@BeforeClass
	public void setUpTest() {
		setDriver();
		home = new HomePage(driver);
		
		UsersService users = new UsersService();
		UsersParent userData = users.retrieveParentAccount("https://jsonplaceholder.typicode.com/users/1");
		firstName = userData.getName();
		lastName = userData.getUsername();
		StreetAddress = userData.getAddress().getStreet();
		OptionalAddress = userData.getAddress().getSuite();
		City = userData.getAddress().getCity();
		State = "AL-Alabama";
		PostalCode = userData.getAddress().getZipcode();
		Telephone = userData.getPhone();
	}
	
	@Test 
	public void openBeachbodyApplication() {
		home.navigateToUrl(ConfigPropertyReader.getProperty("baseUrl"));
		home.findYourProgram();
		productsPage = new ProductsPage(driver);
		productsPage.clickOnAddToCart();
	}
	
	@Test(dependsOnMethods="openBeachbodyApplication")
	public void enterCheckoutDetails() {
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.enterEmailAdress("testUser@test.com");
		checkoutPage.enterShippingDetails(firstName, lastName, StreetAddress, OptionalAddress, City, State, PostalCode, Telephone);
	}
	
	@Test(priority = 1)
	public void testPass() {
		Assert.assertTrue(true);
	}
	
	@Test(priority = 2)
	public void testFail() {
		Assert.fail();
	}
	
	@Test(dependsOnMethods="testFail")
	public void testSkip() {
		System.out.println("Skipped test");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
