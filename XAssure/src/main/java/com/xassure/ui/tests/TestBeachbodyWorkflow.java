package com.xassure.ui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.xassure.api.models.users.UsersParent;
import com.xassure.api.services.UsersService;
import com.xassure.reporting.Reporting;
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
		//Create reports directory
		Reporting.createReportDirectory();

		//Fetch test data from users API
		UsersService users = new UsersService();
		UsersParent userData = users.retrieveParentAccount(getEndpoint());
		firstName = userData.getName();
		lastName = userData.getUsername();
		StreetAddress = userData.getAddress().getStreet();
		OptionalAddress = userData.getAddress().getSuite();
		City = userData.getAddress().getCity();
		State = "AL-Alabama";
		PostalCode = userData.getAddress().getZipcode();
		Telephone = userData.getPhone();
	}

	@BeforeMethod
	private void beforeMethod(Method methodName) {
		Reporting.getLogger(methodName.getName());
	}

	@Test
	public void openBeachbodyApplication() {
		Reporting.getLogger().log(LogStatus.INFO, "User has navigated to beachbody website");
		home.navigateToUrl(ConfigPropertyReader.getProperty("baseUrl"));
		Assert.assertTrue(home.isLogoDisplayed());
		Reporting.getLogger().log(LogStatus.PASS, "ASSERTION PASSED: User is on Beachbody application and Beachbody logo is displayed");
	}

	@Test(dependsOnMethods="openBeachbodyApplication")
	public void chooseProgramAndAddToCart() {
		home.findYourProgram();
		Reporting.getLogger().log(LogStatus.INFO, "User is looking for a program");
		productsPage = new ProductsPage(driver);
		productsPage.clickOnAddToCart();
		Reporting.getLogger().log(LogStatus.PASS, "User successfully added Double Product to Cart" + Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(driver)));

	}

	@Test(dependsOnMethods="chooseProgramAndAddToCart")
	public void enterCheckoutDetails() {
		Reporting.getLogger().log(LogStatus.INFO, "User is entering checkoutdetails");
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.enterEmailAdress("testUser@test.com");
		checkoutPage.enterShippingDetails(firstName, lastName, StreetAddress, OptionalAddress, City, State, PostalCode, Telephone);
		checkoutPage.placeOrder();
		Assert.assertEquals(checkoutPage.getCaptchaAlertText(), "Please complete the Captcha!", "ASSERTION FAILED: Captha Alert text is not as expected");
		Reporting.getLogger().log(LogStatus.PASS, "User has successfully entered email and shipping details" + Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(driver)));

	}

	@Test
	public void testPass() {
		Assert.assertTrue(true);
		Reporting.getLogger().log(LogStatus.PASS, "ASSERTION PASSED: This is dummy pass test message template");
	}

	@Test
	public void testFail() {
		Reporting.getLogger().log(LogStatus.FAIL, "ASSERTION FAILED: This is dummy fail test message template");
		Assert.fail();
	}

	@Test(dependsOnMethods="testFail")
	public void testSkip() {
		Reporting.getLogger().log(LogStatus.SKIP, "ASSERTION SKIPPED: This is dummy skipped test message template");
		System.out.println("Skipped test");
	}

	@AfterMethod
	private void afterMethod(Method methodName) {
		Reporting.generateTestReport(methodName.getName());
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		Reporting.generateReport();
	}
}
