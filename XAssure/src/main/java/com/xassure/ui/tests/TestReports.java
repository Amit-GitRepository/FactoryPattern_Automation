package com.xassure.ui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.xassure.reporting.Reporting;
import com.xassure.ui.pages.HomePage;
import com.xassure.ui.utils.ConfigPropertyReader;

public class TestReports extends BaseTest {

	HomePage home;

	@BeforeClass
	public void setUpTest() {
		Reporting.createReportDirectory();
		setDriver();
		home = new HomePage(driver);
		home.navigateToUrl(ConfigPropertyReader.getProperty("baseUrl"));
	}

	@BeforeMethod
	private void beforeMethod(Method methodName) {
		Reporting.getLogger(methodName.getName());
		
	}

	@AfterMethod
	private void afterMethod(Method methodName) {
		Reporting.generateTestReport(methodName.getName());
	}

	@Test
	public void testDriver() {
//		home.searchComputer("Test");
		System.out.println("Happy");
		Reporting.getLogger().log(LogStatus.PASS, "Test Report"); 
	}

	@Test
	public void testPass() {
		Assert.assertTrue(true);
		Reporting.getLogger().log(LogStatus.PASS, "Test Report");
	}

	@Test
	public void testFail() {
		Reporting.getLogger().log(LogStatus.FAIL, "Test Report Failure");
		Assert.fail();
		
	}

	@Test(dependsOnMethods = "testFail")
	public void testSkip() {
		System.out.println("Skipped test");
	}

	@org.testng.annotations.AfterClass
	public void tearDown() {

		driver.quit();
		Reporting.generateReport();
	}
}
