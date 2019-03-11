package com.xassure.ui.tests;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.xassure.ui.pages.HomePage;
import com.xassure.ui.utils.ConfigPropertyReader;

public class TestDriver extends BaseTest{
	
	HomePage home;
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
	
	@BeforeClass
	public void setUpTest() {
		setDriver();
		home = new HomePage(driver, "HomePage");
		home.navigateToUrl(ConfigPropertyReader.getProperty("baseUrl"));
	}
	
	@Test
	public void testDriver() {
		home.searchComputer("Test");
	
	}
	
	@Test
	public void testPass() {
		Assert.assertTrue(true);
	}
	
	@Test
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
