package com.xassure.ui.wrappers.selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.xassure.ui.tests.BaseTest;
import com.xassure.ui.utils.ConfigPropertyReader;

public class SeleniumWebControls extends BaseTest implements XAssureWebControls{

	WebDriver driver;
	public SeleniumWait wait;
	private String pageName;
	int timeOut, hiddenFieldTimeOut;
	boolean flag = false;;
	static String lastWindow;
	public SeleniumWebControls(WebDriver driver, String pageName) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(ConfigPropertyReader.getProperty(
				"Config.properties", "timeout")));
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void logMessage(String message) {
		Reporter.log(message, true);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public boolean isElementDisplayed(WebElement element) {
		if(element.isEnabled() && element.isDisplayed())
			return true;
		else
			return false;
	}

	public void verifyPageTitleExact(String expectedPagetitle) {
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle 
				.isEmpty()))
				&& (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
			logMessage("ASSERT PASSED: PageTitle for " + pageName
					+ " is exactly: '" + expectedPagetitle + "'");
		} catch (TimeoutException ex) {
			Assert.fail("ASSERT FAILED: PageTitle for " + pageName
					+ " is not exactly: '" + expectedPagetitle
					+ "'!!!\n instead it is :- " + driver.getTitle());
		}
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 * 
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	public void verifyPageTitleContains(String expectedPagetitle) {
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle
				.isEmpty()))
				&& (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("ASSERT FAILED: As actual Page Title: '"
					+ actualPageTitle
					+ "' does not contain expected Page Title : '"
					+ expectedPagetitle + "'.");
			System.out.println("In catch---");
		}
		String actualPageTitle = getPageTitle().trim();
		logMessage("ASSERT PASSED: PageTitle for " + actualPageTitle
				+ " contains: '" + expectedPagetitle + "'.");
	}

	public WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	public WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	public WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	public void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public Object executeJavascript1(Object script) {

		return ((JavascriptExecutor) driver).executeScript(script.toString());
	}

	public void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);

		hoverOver.moveToElement(element).build().perform();
	}

	public void handleAlert() {
		try {
			timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("Config.properties",
					"timeout"));
			hiddenFieldTimeOut = Integer.parseInt(ConfigPropertyReader.getProperty(
					"Config.properties", "hiddenFieldTimeOut"));
			wait.resetImplicitTimeout(2);
			wait.resetExplicitTimeout(hiddenFieldTimeOut);
			switchToAlert().accept();
			logMessage("Alert handled..");
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
			System.out.println("No Alert window appeared...");
		}
	}

	public String getAlertText() {
		try {
			timeOut = Integer.parseInt(ConfigPropertyReader.getProperty("Config.properties",
					"timeout"));
			hiddenFieldTimeOut = Integer.parseInt(ConfigPropertyReader.getProperty(
					"Config.properties", "hiddenFieldTimeOut"));
			wait.resetImplicitTimeout(0);
			wait.resetExplicitTimeout(hiddenFieldTimeOut);
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			logMessage("Alert message is " + alertText);
			alert.accept();
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
			return alertText;
		} catch (Exception e) {
			wait.resetImplicitTimeout(timeOut);
			wait.resetExplicitTimeout(timeOut);
			System.out.println("No Alert window appeared...");
			return null;
		}
	}

	public Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void selectProvidedTextFromDropDown(WebElement el, String text) {

		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		try {
			sel.selectByVisibleText(text);
		} catch (StaleElementReferenceException ex1) {
			// wait.waitForElementToBeVisible(el);
			// scrollDown(el);
			// Select select = new Select(el);
			sel.selectByVisibleText(text);
			logMessage("select Element " + el
					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {

			logMessage("Element " + el + " could not be clicked! "
					+ ex2.getMessage());
		}
	}

	public void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	public void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	public void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			// wait.waitForElementToBeClickable(element);
			// scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element
					+ " after catching Stale Element Exception");
		} catch (WebDriverException ex3) {
			wait.waitForElementToBeClickable(element);
			scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element
					+ " after catching WebDriver Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}
	
	public void enterText(WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}

	public void holdExecution(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchWindow() {
		for (String current : driver.getWindowHandles()) {
			driver.switchTo().window(current);
		}
	}

	public boolean isWindow() {
		String window = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		// check values
		while (iterator.hasNext()) {
			lastWindow = iterator.next().toString();
			System.out.println("last window:" + lastWindow);
		}
		System.out.println("last window:" + lastWindow);
		System.out.println("window:" + window);
		if (!window.equalsIgnoreCase(lastWindow)) {
			flag = true;
		}
		return flag;
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public void navigateToBackPage() {
		driver.navigate().back();
		logMessage("Step : navigate to back page\n");
	}

	public void navigateToUrl(String URL) {
		driver.navigate().to(URL);
		logMessage("STEP : Navigate to URL :- " + URL);
	}
 
	public void selectDropDownValue(WebElement el, int index) {
		try {
			wait.waitForElementToBeVisible(el);
			scrollDown(el);
			Select sel = new Select(el);
			sel.selectByIndex(index);
		} catch (StaleElementReferenceException ex1) {
			// wait.waitForElementToBeVisible(el);
			// scrollDown(el);
			Select sel = new Select(el);
			sel.selectByIndex(index);
			logMessage("select Element " + el
					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + el + " could not be clicked! "
					+ ex2.getMessage());
		}
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void clickUsingXpathInJavaScriptExecutor(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void sendKeysUsingXpathInJavaScriptExecutor(WebElement element,
			String text) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value', '" + text
				+ "')", element);
	}

	public void hardWaitForIEBrowser(int seconds) {
		if (ConfigPropertyReader.getProperty("browser").equalsIgnoreCase("IE")
				|| ConfigPropertyReader.getProperty("browser")
				.equalsIgnoreCase("ie")
				|| ConfigPropertyReader.getProperty("browser")
				.equalsIgnoreCase("internetexplorer")) {
			wait.hardWait(seconds);
		}
	}

	public String getTestCaseID(String methodName) {
		String[] split = methodName.split("_");
		String testCaseID = split[1];
		return testCaseID;
	}

	public void performClickByActionBuilder(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
		builder.moveToElement(element).click().perform();
	}

	public boolean isDropDownValuePresent(List<WebElement> elements,
			String value) {
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(value)) {
				flag = true;
			}
		}
		return flag;
	}

	public String getSelectedTextFromDropDown(WebElement el) {
		try {
			wait.waitForElementToBeVisible(el);
			scrollDown(el);
			Select sel = new Select(el);
			return sel.getFirstSelectedOption().getText();

		} catch (StaleElementReferenceException ex1) {
			// wait.waitForElementToBeVisible(el);
			// scrollDown(el);
			Select sel = new Select(el);
			logMessage("get selected Element " + el
					+ " after catching Stale Element Exception");
			return sel.getFirstSelectedOption().getText();

		} catch (Exception ex2) {
			logMessage("Element " + el + " could not be clicked! "
					+ ex2.getMessage());
			return null;
		}
	}

	public void verifySelectedTextFromDropDown(WebElement el, String text) {
		Assert.assertTrue(getSelectedTextFromDropDown(el)
				.equalsIgnoreCase(text));
		logMessage("AASERT PASSED : " + text
				+ " is verified which is selected \n");
	}

	public void getUrlResponseCode(String url) {
		try {
			URL url1 = new URL(url);
			HttpURLConnection http = (HttpURLConnection) url1.openConnection();
			http.getResponseCode();
			System.out.println(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getElementText(WebElement element) {
		return element.getText();

	}

	public void selectDropDownValue(String value){
		WebElement element=driver.findElement(By.xpath("//select/option[text()='"+value+"']"));
		element.click();
		logMessage("Step : "+value+" is selected in drop down");
	}

	public void checkCheckbox(WebElement ele) {
		if (!ele.isSelected()) {
			ele.click();
			logMessage("Step : check checkbox \n");
		} else {
			logMessage("Step : check box is already selected\n");
		}
	}

	public void ScrollPage(int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

}
