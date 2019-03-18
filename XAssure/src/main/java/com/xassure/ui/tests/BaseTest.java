package com.xassure.ui.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.openqa.selenium.WebDriver;
import com.xassure.ui.driver.DriverFactory;
import com.xassure.ui.utils.ConfigPropertyReader;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class BaseTest {

	public WebDriver driver;
	DriverFactory browserProvider = new DriverFactory();

	String typeOfDriver = ConfigPropertyReader.getProperty("typeOfDriver");
	String browserName = ConfigPropertyReader.getProperty("browser");
	String url = ConfigPropertyReader.getProperty("baseUrl");

	public WebDriver getDriver() {
		return driver;
	}

	protected void setDriver() {
		driver = browserProvider.getDriver(typeOfDriver).init(browserName); 
		if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox"))
			driver.manage().window().maximize();

	}

	public String getEndpoint() {
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader("resources/endpoints.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		JSONObject jo = (JSONObject) obj; 
		return (String) jo.get("UsersApiEndpoint");
	}





}