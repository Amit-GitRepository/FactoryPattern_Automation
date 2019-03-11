package com.xassure.ui.wrappers.selenium;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public interface XAssureWebControls {

	public String getPageTitle();
	public String getCurrentURL();
	public void verifyPageTitleExact(String expectedPagetitle);
	public void verifyPageTitleContains(String expectedPagetitle);
	public WebElement getElementByIndex(List<WebElement> elementlist,
			int index);

	public WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext);

	public WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext);

	public void switchToFrame(WebElement element);

	public void switchToFrame(int i);

	public void switchToFrame(String id);

	public void switchToDefaultContent();

	public void executeJavascript(String script);

	public Object executeJavascript1(Object script);

	public void hover(WebElement element);

	public void handleAlert();

	public String getAlertText();

	public Alert switchToAlert() ;

	public void selectProvidedTextFromDropDown(WebElement el, String text) ;

	public void scrollDown(WebElement element) ;

	public void hoverClick(WebElement element) ;

	public void click(WebElement element) ;

	public void holdExecution(int milliSeconds) ;

	public void switchWindow() ;

	public boolean isWindow() ;

	public void pageRefresh();

	public void navigateToBackPage() ;

	public void navigateToUrl(String URL) ;

	public void selectDropDownValue(WebElement el, int index) ;

	public void deleteAllCookies() ;

	public void clickUsingXpathInJavaScriptExecutor(WebElement element) ;

	public void sendKeysUsingXpathInJavaScriptExecutor(WebElement element,
			String text) ;

	public void hardWaitForIEBrowser(int seconds) ;

	public String getTestCaseID(String methodName) ;

	public void performClickByActionBuilder(WebElement element) ;

	public boolean isDropDownValuePresent(List<WebElement> elements,
			String value) ;

	public String getSelectedTextFromDropDown(WebElement el) ;

	public void verifySelectedTextFromDropDown(WebElement el, String text) ;

	public String getElementText(WebElement element) ;

	public void selectDropDownValue(String value) ;

	public void checkCheckbox(WebElement ele) ;

	public void ScrollPage(int x, int y) ;

}
