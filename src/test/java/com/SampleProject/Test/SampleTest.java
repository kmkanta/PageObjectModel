package com.SampleProject.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.SampleProject.TestBase.TestBase;
import com.SampleProject.Utils.Utils;

public class SampleTest extends TestBase {
	@Autowired
	Utils utils;

	@FindBy(xpath = "//ul[@class='dropdown-menu']//li/a")
	List<WebElement> ele;

	@BeforeMethod
	public void beforeMethod() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sampleTest() throws InterruptedException {
		utils.toFindAllLinks();
	}
}
