package com.xassure.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.xassure.ui.wrappers.selenium.SeleniumWebControls;

public class CheckoutPage extends SeleniumWebControls{

	public CheckoutPage(WebDriver driver) {
		super(driver, "CheckoutPage");
	}
	
	@FindBy(id="email")
	public WebElement emailId_textbox;
	
	@FindBy(id="creditCardNumber")
	public WebElement creditCardNumber_textBox;
	
	@FindBy(id="cvv2")
	public WebElement cvv2_textbox;
	
	@FindBy(id="shippingFirstName")
	public WebElement shippingFirstName_textbox;
	
	@FindBy(id="shippingLastName")
	public WebElement shippingLastName_textbox;
	
	@FindBy(id="street1")
	public WebElement streetAdressLine1_textbox;
	
	@FindBy(id="street2")
	public WebElement streetAddressLine2_textbox;
	
	@FindBy(id="city")
	public WebElement city_textbox;
	
	@FindBy(id="state1")
	public WebElement state_dropdown;
	
	@FindBy(id="postalCode")
	public WebElement postalCode_textbox;
	
	@FindBy(id="phoneInput")
	public WebElement telephoneInput_textbox;
	
	public void enterEmailAdress(String emailAddress) {
		enterText(emailId_textbox, emailAddress);
	}
	
	public void enterShippingDetails(String firstName, String lastName, String StreetAddress, String OptionalAddress, String City, String state, String PostalCode, String Telephone) {
		enterText(shippingFirstName_textbox, firstName);
		enterText(shippingLastName_textbox, lastName);
		enterText(streetAdressLine1_textbox, StreetAddress);
		enterText(streetAddressLine2_textbox, OptionalAddress);
		enterText(city_textbox, City);
		selectProvidedTextFromDropDown(state_dropdown, state);
		enterText(telephoneInput_textbox, Telephone);
		
	}

	
	
	

}
