package com.test.utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonTask {

	public static boolean waitForElementpresent(WebDriver driver, By element) {

		WebElement webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			webElement = driver.findElement(element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (webElement != null && webElement.isDisplayed() && webElement.isEnabled())
			return true;
		else
			return false;
	}

	public static String handlePopUps(WebDriver driver) throws InterruptedException {

		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		return parentWindowHandler;
	}
}
