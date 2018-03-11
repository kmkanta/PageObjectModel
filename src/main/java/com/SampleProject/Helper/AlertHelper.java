package com.SampleProject.Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(AlertHelper.class);

	
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHelper : " + this.driver.hashCode());
	}
	/*
	 * To switch driver into Alert Box
	 *  
	 */

	public Alert getAlert() {
		return driver.switchTo().alert();
	}
	/*
	 * To Accept Alert Box
	 *  
	 */
	public void acceptAlert() {
		getAlert().accept();
	}
	/*
	 * To Dismiss Alert Box
	 *  
	 */
	public void dismissAlert() {
		getAlert().dismiss();
	}
	/*
	 * To get text from Alert Box
	 *  
	 */
	public String getAlertText() {
		String text = getAlert().getText();
		log.info(text);
		return text;
	}
	/*
	 * To verify Alert is Present
	 *  
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			log.info("false");
			return false;
		}
	}
	/*
	 * If Alert is present then Accept the Alert
	 *  
	 */
	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		acceptAlert();
		log.info("");
	}
	/*
	 * If Alert present then Dismiss Alert
	 *  
	 */
	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		dismissAlert();
		log.info("");
	}

	public void AcceptPrompt(String text) {

		if (!isAlertPresent())
			return;

		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
	
}
