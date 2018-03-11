package com.SampleProject.Utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.SampleProject.Helper.LoggerHelper;
@Component
public class Utils {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(Utils.class);

	public Utils(WebDriver driver) {
		this.driver = driver;
		log.info("Utils : " + this.driver.hashCode());
	}

	public void toFindAllLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));

		log.info("Total No.Of Links are : " + links.size());

		for (int i = 0; i < links.size(); i++) {

			WebElement ele = links.get(i);

			String url = ele.getAttribute("href");

			verifyLinkActive(url);

		}

	}

	// To find Broken links in WebPage
	public void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				log.info(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				log.info(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}

	}
}
