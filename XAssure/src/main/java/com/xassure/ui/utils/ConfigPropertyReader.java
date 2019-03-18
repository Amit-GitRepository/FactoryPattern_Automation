package com.xassure.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This is the utility class for data read write
 *
 * @author amit
 *
 */
public class ConfigPropertyReader {

	private static String defaultConfigFile = "./Config.properties";

	/**
	 * construtor of this class
	 */
	public ConfigPropertyReader() { 
	}

	/**
	 *
	 * This method will get the properties pulled from a file located relative to the base dir
	 *
	 * @param propFile complete or relative (to base dir) file location of the properties file
	 * @param Property property name for which value has to be fetched
	 * @return String value of the property
	 */


	public static String getProperty(String propFile, String Property) {
		Properties props = new Properties();
		FileInputStream file = null;

		try {
			file = new FileInputStream(propFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			props.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = props.getProperty(Property);
		return value;
	}


	public static String getProperty(String property){
		return getProperty(defaultConfigFile, property);
	}
}
