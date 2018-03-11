package com.SampleProject.Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClassHelper {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ActionClassHelper.class);
	
	public ActionClassHelper(WebDriver driver) {
		this.driver = driver;
		log.info("ActionClassHelper : "+this.driver.hashCode());
	}
	/*
	 * To perform Drag and Drop operation
	 * 
	 * @param source
	 * 
	 * @param target
	 *  
	 */
	
	public void dragAndDrop(WebElement source , WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();
		//actions.clickAndHold(source).moveToElement(target).release(target).build().perform();
	}
	/*
	 * To perform move to particular webElement
	 * 
	 * @param element
	 *  
	 */
	public void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

}
