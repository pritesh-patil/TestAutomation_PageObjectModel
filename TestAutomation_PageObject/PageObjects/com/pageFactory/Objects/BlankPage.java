package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.test.utils.CommonTask;

public class BlankPage {

	WebDriver driver;

	By publishBtn = By.id("rte-button-publish");
	By closeBtn = By.id("rte-button-cancel");
	By pageTitle = By.id("content-title");
	By pageBody = By.id("tinymce");
	
	By restictBtn = By.id("rte-button-restrictions");
	By restrictionHeaderLbl = By.xpath("//h2[contains(text(),'Restrictions')]");
	By restrictoptionDropDown = By.xpath("//span[@class='select2-arrow']");
	By restrictSearchUserGroup = By.id("s2id_autogen10");
	By restrictPopUpAddBtn = By.id("page-restrictions-add-button");
	By restirctPopUpApplyBtn = By.id("page-restrictions-dialog-save-button");
	By restrictPopUpCancelLnk = By.id("page-restrictions-dialog-close-button");
	By restrictPopUpViewEditDropDown = By.id("page-restrictions-permission-selector");

	public BlankPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyBlankPageTitle() throws InterruptedException {
		String expectedTitle = "Add Page - Confluence";
		return getBlankPageTitle().contains(expectedTitle);
	}

	private String getBlankPageTitle() throws InterruptedException {
		Thread.sleep(2000);
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public void enterPageName(String pageName) {
		if (CommonTask.waitForElementpresent(driver, pageTitle))
			driver.findElement(pageTitle).sendKeys(pageName);
		else
			System.out.println("Page Title is not found or loaded");
	}

	public void enterPageBodyConent() {
		if (CommonTask.waitForElementpresent(driver, pageBody))
			driver.findElement(pageBody).sendKeys(
					"Page Object Model is an Object Repository design pattern in Selenium WebDriver. POM creates our testing code maintainable, reusable. Page Factory is an optimized way to create object repository in POM concept");
		else
			System.out.println("Page body content is not found or loaded");
	}

	public HomePage clickCloseBtn() {
		if (CommonTask.waitForElementpresent(driver, closeBtn)) {
			driver.findElement(closeBtn).click();
			return new HomePage(driver);
		} else {
			System.out.println("Close button not found");
			return null;
		}
	}

	public void clickRestricBtndropDown() {
		if (CommonTask.waitForElementpresent(driver, restrictoptionDropDown))
			driver.findElement(restrictoptionDropDown).click();
		else
			System.out.println("Drop Dwon not found");
	}

	public void clickrestrictBtn() {
		if (CommonTask.waitForElementpresent(driver, closeBtn))
			driver.findElement(closeBtn).click();
		else
			System.out.println("Restrict button not found");
	}

	public void clickAddBtn() {
		if (CommonTask.waitForElementpresent(driver, restrictPopUpAddBtn))
			driver.findElement(restrictPopUpAddBtn).click();
		else
			System.out.println("Add button not found");
	}

	public void clickApplyBtn() {
		if (CommonTask.waitForElementpresent(driver, restirctPopUpApplyBtn))
			driver.findElement(restirctPopUpApplyBtn).click();
		else
			System.out.println("Apply button not found");
	}

	public void clickCancelLnk() {
		if (CommonTask.waitForElementpresent(driver, restrictPopUpCancelLnk))
			driver.findElement(restrictPopUpCancelLnk).click();
		else
			System.out.println("Cancel link not found");
	}

	public void searchUserAndGroup(String userAndGroup) {
		driver.findElement(restrictSearchUserGroup).sendKeys(userAndGroup);
	}

	public void selectViewOrEdit(String option) {

		if (CommonTask.waitForElementpresent(driver, restrictPopUpViewEditDropDown)) {
			driver.findElement(restrictPopUpCancelLnk).click();
			Select ViewOrEdit = new Select(driver.findElement(restrictPopUpViewEditDropDown));
			ViewOrEdit.selectByVisibleText(option);
		} else
			System.out.println("Can View or Edit Select not found");

	}

	public PagesDetailPage clickPublishBtn() {
		if (CommonTask.waitForElementpresent(driver, publishBtn)) {
			driver.findElement(publishBtn).click();
			return new PagesDetailPage(driver);
		} else {
			System.out.println("Publish button not found");
			return null;
		}

	}

	public PagesDetailPage enterDetailsInPage(String pageName, String pageAction) {

		this.enterPageName(pageName);
		switch (pageAction.toLowerCase()) {
		case "publish":

			return this.clickPublishBtn();

		case "close":
			this.clickCloseBtn();
			break;
		default:
			new IllegalArgumentException("Action is not correct");
		}
		return null;
	}
}
