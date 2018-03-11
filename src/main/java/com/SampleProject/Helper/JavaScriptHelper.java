package com.SampleProject.Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper : " + this.driver.hashCode());

	}

	/*
	 * To perform JavaScript execution
	 * 
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script);
	}

	/*
	 * To perform JavaScript execution
	 * 
	 * @param script
	 * 
	 * @param args
	 */
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script, args);
	}

	/*
	 * To perform scroll into particular WebElement
	 * 
	 * @param element
	 * 
	 */
	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info(element);
	}

	/*
	 * To perform scroll into particular WebElement and perform click operation
	 * 
	 * @param element
	 * 
	 */
	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		log.info(element);
	}

	/*
	 * To perform scroll into particular WebElement
	 * 
	 * @param element
	 * 
	 */
	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info(element);
	}

	/*
	 * To perform scroll into particular WebElement and click
	 * 
	 * @param element
	 * 
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info(element);
	}

	/*
	 * To perform scroll Down operation vertically
	 * 
	 */
	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/*
	 * To perform scroll Up operation vertically
	 * 
	 */
	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	/*
	 * To perform scroll Down operation by pixels
	 * 
	 */
	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	/*
	 * To perform scroll Up operation by pixels
	 * 
	 */
	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	/*
	 * To perform Zoom operation by percentage
	 * 
	 */
	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	/*
	 * To perform Zoom operation by 100 percentage
	 * 
	 */
	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	/*
	 * Scroll to particular web element
	 * 
	 * @param element
	 * 
	 */
	public void ScrollByElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickOperation(WebElement element) {
		try {
			executeScript("arguments[0].click();", element);
			log.info("To Perform Click Operation on : " + element);
		} catch (Exception e) {
			log.info("Not able to click on element : " + element);
		}
	}

	public void sendKeysOperation(WebElement element, String text) {
		try {
			executeScript("arguments[0].value = '\" + text + \"'\"", element);
			log.info("To Perform SendKeys Operation on : " + element);
		} catch (Exception e) {
			log.info("Not able to SendKeys on element : " + element);
		}
	}
}
