package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.utils.CommonTask;

public class LoginPage {

	WebDriver driver;

	private By userNameTxtBox = By.id("username");
	private By userPasswordTxtBox = By.id("password");
	private By continueBtn = By.xpath("//span[contains(text(),'Continue')]");
	private By logInBtn = By.xpath("//span[text()='Log in']");
	private By logInWithGoogleLnk = By.xpath("//span[text()='Log in with Google']");
	private By incorrectLoginLbl = By.xpath("//span[text()='Incorrect email address and / or password.']");

	public LoginPage(WebDriver driver) {

		this.driver = driver;

	}

	public void setUserName(String strUserName) {

		if (driver != null) {
			try {
				driver.findElement(userNameTxtBox).sendKeys(strUserName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Driver is null");
	}

	public void clickContinueBtn() {

		driver.findElement(continueBtn).click();

	}

	public void setPassword(String strPassword) {

		driver.findElement(userPasswordTxtBox).sendKeys(strPassword);

	}

	public HomePage clickLogInBtn() {

		System.out.println("clicking on sign in button");
		WebElement signInBtnElement = driver.findElement(logInBtn);
		if (signInBtnElement.isDisplayed() && signInBtnElement.isEnabled()) {
			driver.findElement(logInBtn).click();
			return new HomePage(driver);
		} else {
			System.out.println("Log in button not found");
			return null;
		}

	}

	public boolean verifyLogInPageTitle() throws InterruptedException {
		String expectedTitle = "Log in to continue - Log in with Atlassian account";
		return getLogInPageTitle().contains(expectedTitle);
	}

	public boolean verifyLogInPageAfterLogOut() throws InterruptedException {
		
		if (CommonTask.waitForElementpresent(driver, incorrectLoginLbl)) {
			return true;
		} else
			return false;

	}

	private String getLogInPageTitle() throws InterruptedException {
		Thread.sleep(2000);
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public HomePage loginToApplication(String strUserName, String strPasword) throws InterruptedException {

		System.out.println("loginToApplication");
		this.setUserName(strUserName);
		this.clickContinueBtn();
		Thread.sleep(2000);
		this.setPassword(strPasword);
		return (this.clickLogInBtn());
	}
	
	
}
