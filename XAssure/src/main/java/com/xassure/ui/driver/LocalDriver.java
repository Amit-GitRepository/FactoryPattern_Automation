package com.xassure.ui.driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalDriver implements IDriver{ 

	public WebDriver init(String browserName) {
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/resources/geckodriver");
			return new FirefoxDriver();
		} 

		else if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");
			return new ChromeDriver();
		}

		else if(browserName.equalsIgnoreCase("mobile")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");

			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Galaxy S5");

			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);            
			return new ChromeDriver(capabilities);
		}
		else return null;

	}
}