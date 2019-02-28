package com.xassure.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.xassure.pages.HomePage;
import com.xassure.utils.ConfigPropertyReader;

public class TestDriver extends BaseTest{
	
	HomePage home;
	
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

}
