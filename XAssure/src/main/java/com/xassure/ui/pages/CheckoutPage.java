package com.xassure.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.xassure.ui.wrappers.selenium.SeleniumWebControls;

public class CheckoutPage extends SeleniumWebControls{

	public CheckoutPage(WebDriver driver) {
		super(driver, "CheckoutPage");
	}

	@FindAll({
		@FindBy(id="email"),
		@FindBy(xpath = "//input[@type='text'][@id='email']"),
		@FindBy(xpath = "//fieldset[@id='set1']/input"),
		@FindBy(className="wrongClassName")
	})
	public WebElement logoBeachbody_image;

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
	
	@FindBy(xpath="//input[@id='submitIntOrder']")
	public WebElement placeOrder_button;
	
	@FindBy(id="errorTC")
	public WebElement errorTermsAndConditions_label;

	@FindBy(id="phoneInput")
	public WebElement telephoneInput_textbox; 
	
	@FindBy(id="legalTermsCondtionCheck")
	public WebElement legalTermsCondtion_checkbox; 

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
	
	public void placeOrder() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(legalTermsCondtion_checkbox);
		click(placeOrder_button);
	}
	
	public String getCaptchaAlertText() {
		switchToAlert();
		return getAlertText();
	}
	
	public boolean isTermsAndConditionsErrorDisplayed() {
		return isElementDisplayed(errorTermsAndConditions_label);
	}





}
