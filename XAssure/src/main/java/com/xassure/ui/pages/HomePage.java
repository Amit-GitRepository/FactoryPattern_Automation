package com.xassure.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.xassure.ui.wrappers.selenium.SeleniumWebControls;

public class HomePage extends SeleniumWebControls{

	public HomePage(WebDriver driver, String pageName) {
		super(driver, pageName);
		
	}
	
	@FindBy(id="searchbox")
	public WebElement searchComputer_textBox;
	
	@FindAll({
		@FindBy(id = "searchsubmit"), 
		@FindBy(xpath = "//input[@value='Filter by name']")
		})
	public WebElement searchComputer_button;
		
	
	
	public void searchComputer(String computerName) {
		enterText(searchComputer_textBox, computerName);
		click(searchComputer_button);
	}
	
	
	

}
