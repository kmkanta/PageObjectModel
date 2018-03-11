package com.SampleProject.Helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHelper {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("DropDownHelper : " + this.driver.hashCode());
	}

	/*
	 * Select Item In DropDown (by index)
	 * 
	 * @Param element
	 * 
	 * @Param index
	 * 
	 */

	public void selectItemInDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Locator : " + element + " Value : " + index);
	}

	/*
	 * Select Item In DropDown (by text)
	 * 
	 * @Param element
	 * 
	 * @Param text
	 * 
	 */
	public void selectItemInDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		log.info("Locator : " + element + " Value : " + text);
	}

	/*
	 * Select Item In DropDown (by value)
	 * 
	 * @Param element
	 * 
	 * @Param htmlTextValue
	 * 
	 */
	public void selectItemInDropdown(String htmlTextValue, WebElement element) {
		Select select = new Select(element);
		select.selectByValue(htmlTextValue);
		log.info("Locator : " + element + " Value : " + htmlTextValue);
	}

	/*
	 * Get All Items In DropDown
	 * 
	 * @Param locator
	 * 
	 */
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();

		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}

	/*
	 * To Get Selected Text In DropDown
	 * 
	 * @Param element
	 * 
	 * @param compareText
	 * 
	 */
	public String getSelectedTextInDropdown(WebElement element, String compareText) {
		List<WebElement> options = new Select(element).getAllSelectedOptions();
		for (WebElement option : options) {
			if (option.getText().equals(compareText)) {
				return option.getText();
			}
			log.info("Locator : " + element + " Value : " + compareText);
		}
		return null;
	}

	/*
	 * Verify DropDown Items
	 * 
	 * @param element
	 */
	public void verifyDropDownItems(WebElement element) {
		String[] expectedItems = { "Month", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV",
				"DEC" };
		Select select = new Select(element);
		List<WebElement> actualItems = select.getOptions();
		for (WebElement options : actualItems) {
			for (int i = 0; i < expectedItems.length; i++) {
				if (options.getText().equals(expectedItems[i])) {
					System.out.println("Matched");
					log.info("Matched");
				}
			}

		}

	}

	/*
	 * To verify selected text in dropDown
	 * 
	 * @Param element
	 * 
	 * @param selectedText
	 * 
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void verifySelectedTextInDropdownIs(WebElement element, String selectedText) {
		Boolean found = false;
		Select select = new Select(element);
		List<WebElement> allOptions = select.getOptions();
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.equals(selectedText)) {
				found = true;
				break;
			}
		}
		if (found) {
			log.info("Selected value is exist");
			System.out.println("Selected value is exist");
		}

	}

	/*
	 * To get selected text from dropDown
	 * 
	 * @Param element
	 * 
	 */
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : " + value);
		return value;
	}

	/*
	 * Verify Selected element is presented
	 * 
	 * @param element
	 */
	public boolean verifyElementPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			isDispalyed = element.isDisplayed();
			log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
		}

		return isDispalyed;
	}
	/*
	 * Verify Selected element not presented
	 * 
	 * @param element
	 */

	public boolean verifyElementNotPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			element.isDisplayed();
			log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}

		return isDispalyed;
	}
	/*
	 * Verify Selected element present and match the Text
	 * 
	 * @param element
	 * 
	 * @param expectedText
	 */

	public boolean verifyTextEquals(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)) {
				log.info("actualText is :" + actualText + " expected text is: " + expectedText);
				return flag = true;
			} else {
				log.error("actualText is :" + actualText + " expected text is: " + expectedText);
				return flag;
			}
		} catch (Exception ex) {
			log.error("actualText is :" + element.getText() + " expected text is: " + expectedText);
			log.info("text not matching" + ex);
			return flag;
		}
	}

	public void selectBootstrapDropDown(WebElement element, List<WebElement> list, String selectedText) {
		element.click();
		for (WebElement ele : list) {
			log.info("All Values in DropDown are : " + ele.getAttribute("innerHTML"));

			if (ele.getAttribute("innerHTML").contains("text")) {
				ele.click();
				break;
			}
		}
	}

}
