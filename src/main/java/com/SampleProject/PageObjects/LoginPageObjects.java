package com.SampleProject.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SampleProject.Helper.ElementHelper;
import com.SampleProject.TestBase.TestBase;

public class LoginPageObjects extends TestBase {
	public ElementHelper elementHelper;

	public static final Logger log = Logger.getLogger(LoginPageObjects.class.getName());

	@FindBy(name = "email")
	WebElement userName;

	@FindBy(name = "pass")
	WebElement password;

	@FindBy(xpath = "//*[@value='Log In']")
	WebElement loginButton;

	@FindBy(partialLinkText ="Sign up for Facebook")
	WebElement signupLink;

	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToCRMPROApplication(String userName, String password) {
		elementHelper = new ElementHelper(driver);
		elementHelper.enterTextInElement(this.userName, userName);
		elementHelper.enterTextInElement(this.password, password);
		elementHelper.clickElement(loginButton);
	}

	public String getSignUpText() {
		elementHelper = new ElementHelper(driver);
		return elementHelper.getElementText(signupLink);
	}
}
