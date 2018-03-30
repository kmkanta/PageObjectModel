package com.SampleProject.Test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.SampleProject.PageObjects.LoginPageObjects;
import com.SampleProject.TestBase.TestBase;

public class TC001_LoginPageTest extends TestBase {
	public static final Logger log = Logger.getLogger(TC001_LoginPageTest.class.getName());
	LoginPageObjects loginPageObjects;
	// String emailAddress = "automation@gmail.com";
	String emailAddress = System.currentTimeMillis() + "@gmail.com";
	
	@BeforeMethod
	public void startTest() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void verifyLoginApplicationTest() {
		log.info("=======started verifyLogin Test===========");
		loginPageObjects = new LoginPageObjects(driver);
		loginPageObjects.loginToCRMPROApplication("naveenk", "test@123");
		Assert.assertEquals( loginPageObjects.getSignUpText(),"Sign up for Facebook");
		log.info("=======finished verifyLogin Test===========");

	}
}
