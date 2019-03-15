package com.xassure.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.xassure.ui.wrappers.selenium.SeleniumWebControls;

public class ProductsPage extends SeleniumWebControls{

	public ProductsPage(WebDriver driver) {
		super(driver, "ProductsPage");
		
	}
	
	@FindBy(xpath="(//input[@name='addToBasket'])[3]")
	public WebElement addToCard_button;
	
	
	public void clickOnAddToCart() {
		click(addToCard_button);
	}

	
	
	

}
