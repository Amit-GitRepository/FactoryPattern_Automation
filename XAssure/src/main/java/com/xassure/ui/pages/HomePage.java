package com.xassure.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.xassure.ui.wrappers.selenium.SeleniumWebControls;

public class HomePage extends SeleniumWebControls{
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver, "HomePage");
		
	}
	
	@FindBy(xpath="//div[@class='cart-container']/a")
	public WebElement cart_icon;
	
	@FindBy(xpath="(//a[@title='Find your program'])[1]")
	public WebElement findYourProgram_button;
	
	@FindAll({
		@FindBy(id = "searchsubmit"), 
		@FindBy(xpath = "//input[@value='Filter by name']")
		})
	public WebElement searchComputer_button;
		
	
	
	public void clickOnCartIcon() {
		click(cart_icon);
	}
	
	public void findYourProgram() {
		click(findYourProgram_button);
	}
	
	
	

}
