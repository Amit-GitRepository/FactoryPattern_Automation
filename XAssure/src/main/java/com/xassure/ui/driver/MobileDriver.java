package com.xassure.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class MobileDriver implements IDriver {

	public AppiumDriver init(String browserName) {
		DesiredCapabilities capabilities = null;
		AndroidDriver driver = null;
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

}