package com.xassure.ui.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileWebTest {
	
	AppiumDriver<WebElement> driver = null;

	@BeforeTest
	public void setDriver2() {

		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");

		try {
			driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void test() {
//		driver.get("https://www.beachbody.com/");
		System.out.println("hi");
	}
	
}
