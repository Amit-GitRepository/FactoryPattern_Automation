package com.xassure.ui.driver;

public class DriverFactory {

	public IDriver getDriver(String typeOfDriver) {
		if(typeOfDriver.equalsIgnoreCase("local")) 
			return new LocalDriver();
		else if(typeOfDriver.equalsIgnoreCase("remote")) 
			return new RemoteDriver();

		return null;

	}
}