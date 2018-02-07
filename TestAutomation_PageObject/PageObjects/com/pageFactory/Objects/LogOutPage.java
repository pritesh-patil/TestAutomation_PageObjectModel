package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.utils.CommonTask;

public class LogOutPage {

	WebDriver driver;

	By logOutHeaderLbl = By.xpath("//h2[text()='Log out of your Atlassian account']");
	By logOutBtn = By.id("logout-submit");

	public LogOutPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyLogOutHeaderLbl() {
		if (CommonTask.waitForElementpresent(driver, logOutHeaderLbl))
			return true;
		else
			return false;
	}

	public boolean verifyLogOutBtn() {
		if (CommonTask.waitForElementpresent(driver, logOutBtn))
			return true;
		else
			return false;
	}

	public LoginPage clicklogOutBtn() {

		System.out.println("clicking on Log out button");
		WebElement signInBtnElement = driver.findElement(logOutBtn);
		if (signInBtnElement.isDisplayed() && signInBtnElement.isEnabled()) {
			driver.findElement(logOutBtn).click();
			return new LoginPage(driver);
		} else {
			System.out.println("Log in button not found");
			return null;
		}
	}

	public LoginPage logOutToApplication() {
		return (this.clicklogOutBtn());
	}
}
