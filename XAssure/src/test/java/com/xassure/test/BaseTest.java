package com.xassure.test;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import com.xassure.driver.DriverFactory;
import com.xassure.utils.ConfigPropertyReader;

public class BaseTest {

	public WebDriver driver;
	DriverFactory browserProvider = new DriverFactory();
	
	String modeOfExecution = ConfigPropertyReader.getProperty("modeOfExecution");
	String browserName = ConfigPropertyReader.getProperty("browser");
	String url = ConfigPropertyReader.getProperty("baseUrl");
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected void setDriver() {
		driver = browserProvider.getDriver(modeOfExecution).init(browserName);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}