package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.utils.CommonTask;

public class CreatePage {

	WebDriver driver;

	By helplink_createPage_popUp = By.linkText("https://confluence.atlassian.com/display/ConfCloud/Pages+and+Blogs"); //// div/a[contains(text(),'Help')]
	By selectSpaceControllerDropdwon = By.className("space-select-control-container");
	By searchSpaceInputBox = By.xpath("//input[@placeholder='Filter spaces ...']");
	By selectSearchedSpace = By.xpath("//div[@class='select2-result-label']/span[@class='select2-match']");
	By createBtn_popUp = By.xpath("//div[@class='dialog-button-panel']/button[contains(text(),'Create')]");
	By createPopUpLbl = By.xpath("//h2[contains(text(),'Create')]");	
	By pageType_BlankPage = By.xpath("//li/div/div[contains(text(),'Blank page')]");

	public CreatePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyCreatePopUpLabel() {
		if (CommonTask.waitForElementpresent(driver, createPopUpLbl))
			return true;
		else
			return false;
	}

	public void clickSelectSpace() {
		if (CommonTask.waitForElementpresent(driver, selectSpaceControllerDropdwon))
			driver.findElement(selectSpaceControllerDropdwon).click();
		else
			System.out.println("Select Space Drop down Controller not found");
	}

	public void searchSpace(String spaceName) {

		if (CommonTask.waitForElementpresent(driver, searchSpaceInputBox))
			driver.findElement(searchSpaceInputBox).sendKeys(spaceName);
		else
			System.out.println("Not able to enter Space in space search box");
	}

	public void selectSearchedSpace() throws InterruptedException {

		if (CommonTask.waitForElementpresent(driver, selectSearchedSpace)) {
			driver.findElement(selectSearchedSpace).click();
			Thread.sleep(2000);
		} else
			System.out.println("Not able to Select Space in from drop down");
	}

	public void clickPageType_BlankPageType() {

		if (CommonTask.waitForElementpresent(driver, pageType_BlankPage)) {
			driver.findElement(pageType_BlankPage).click();
		} else
			System.out.println("Blank Page type is not found or clicked");
	}
	
	public void clickCreatBtnPopUp() {

		if (CommonTask.waitForElementpresent(driver, createBtn_popUp)) {
			driver.findElement(createBtn_popUp).click();
		} else
			System.out.println("Create button is not found or clicked");
	}
	
	private void selctSpaceType(String spaceName) throws InterruptedException {
		this.clickSelectSpace();
		Thread.sleep(1000);
		this.searchSpace(spaceName);
		Thread.sleep(1000);
		this.selectSearchedSpace();
		Thread.sleep(1000);
	}

	public Object createpage(String spaceName,String pageType) throws InterruptedException {
		
		Object PageObject=null;
		switch (pageType) {
		case "Blank page":
			this.selctSpaceType(spaceName);
			this.clickPageType_BlankPageType();
			this.clickCreatBtnPopUp();	
			Thread.sleep(3000);
			PageObject = (Object) new BlankPage(driver);
			break;
		case "Decision":
			break;
		case "File list":
			break;
		case "How-to article":
			break;
		default:
			throw new IllegalArgumentException("Page type is not in correct format");
		}
		return PageObject;
	}
}
