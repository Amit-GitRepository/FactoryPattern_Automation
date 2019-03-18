package com.xassure.ui.driver;


public class DriverFactory {

	public IDriver getDriver(String typeOfDriver) {
		if(typeOfDriver.equalsIgnoreCase("local")) 
			return new LocalDriver();
		else if(typeOfDriver.equalsIgnoreCase("remote"))
			return new RemoteDriver();
		else if(typeOfDriver.equalsIgnoreCase("mobile"))
			return new MobileDriver();
		return null;
	}
}