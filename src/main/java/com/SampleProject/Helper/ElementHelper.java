package com.SampleProject.Helper;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class ElementHelper {
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ElementHelper.class);

	public ElementHelper(WebDriver driver) {
		this.driver = driver;
		log.info("Element : " + this.driver.getTitle());
	}

	public void clickElement(WebElement element) {
		try {
			element.click();
			log.info("To Perform Click Operation on : " + element);
		} catch (Exception e) {
			log.info("Not able to click on element : " + element);
		}
	}

	public void clickCoordinates(WebElement element, int xOffset, int yOffset) {
		Actions actions = new Actions(driver);
		try {
			actions.moveToElement(element, xOffset, yOffset).click().build().perform();
			log.info("To Perform Click Operation on on Coordinates : " + element);
		} catch (Exception e) {
			log.info("Not able to click on Coordinates : " + element);
		}
	}

	public void enterTextInElement(WebElement element, String text) {
		try {
			element.clear();
			element.sendKeys(text);
			log.info("Entered text into Element and text is : " + text);
		} catch (Exception e) {
			log.info("Not able to enter text into element : " + element);
		}
	}

	public String getElementCSSValue(WebElement element, String attribute) {
		String cssValue = "";
		try {
			cssValue = element.getCssValue(attribute);
			log.info("To get Element CSS value : " + cssValue);
		} catch (Exception e) {
			log.info("Not able to get Element CSS value : " + element);
		}
		return cssValue;
	}

	public int getElementWidth(WebElement element) {
		int width = 0;
		try {
			width = element.getSize().getWidth();
			log.info("The width of Element is : " + width);
		} catch (Exception e) {
			log.info("Unable to get width of the Element : " + element);
		}
		return width;
	}

	public void getCoordinatesOfElement(WebElement element) {
		try {
			Point point = element.getLocation();
			log.info("X Cordinates of selected Element is : " + point.getX());
			log.info("Y Cordinates of selected Element is : " + point.getY());
		} catch (Exception e) {
			log.info("Not able to Identifi the X and Y Coordinates :- ");
		}
	}

	public String getElementText(WebElement element) {
		String text ="";
		try {
			 text = element.getText();
			log.info("The Text of the Element is : " + text);
		} catch (Exception e) {
			log.info("Unable to get the text of the Element : " + element);
		}
		return text;
	}

	public void getAllLinksCount() {
		try {
			List<WebElement> element = driver.findElements(By.tagName("a"));
			log.info("All Links in Current Web Page : " + element.size());
			for (WebElement allElements : element) {
				log.info("All Links Text : " + allElements.getText());
			}
		} catch (Exception e) {
			log.info("Not able to find the Links");
		}
	}

	public void elementIsDisplayed(WebElement element) {
		boolean elementStatus = element.isDisplayed();
		log.info("element is Displayed and Status is : "+elementStatus);
		Assert.assertTrue(elementStatus);
	}
	public void elementIsEnabled(WebElement element) {
		boolean elementStatus = element.isEnabled();
		log.info("element is Enabled and Status is : "+elementStatus);
		Assert.assertTrue(elementStatus);
	}

	public void elementIsSelected(WebElement element) {
		element.click();
		boolean elementStatus = element.isSelected();
		log.info("element is Selected and Status is : "+elementStatus);
		Assert.assertTrue(elementStatus);
	}
}
