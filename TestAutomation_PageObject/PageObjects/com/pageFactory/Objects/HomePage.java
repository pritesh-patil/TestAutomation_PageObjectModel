package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.utils.CommonTask;

public class HomePage {

	WebDriver driver;

	By activityHeaderLbl = By.xpath("//h1[@class='DashboardTab_listTitle_G7Z list-title']/span[text()='Activity']");
	By profileNsettingimg = By.xpath("//span[@class='kIBPYg']");
	By logOutLink = By.xpath("//span[text()='Log Out']");

	By createPageBtn = By.id("create-page-button");

	By SpacesLbl = By.xpath("//span[@class='icVecj' and contains(text(),'Spaces')]");

	By createSpaceLink = By.id("addSpaceLink");
	By homeLogo = By.id("logo");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyActivityHeaderLbl() {

		if (CommonTask.waitForElementpresent(driver, activityHeaderLbl)) {
			return true;
		} else
			return false;
	}

	public boolean verifiyProfileNsettingimg() {

		if (CommonTask.waitForElementpresent(driver, profileNsettingimg)) {
			return true;
		} else
			return false;
	}

	public CreatePage click_createPageBtn() throws InterruptedException {

		if (CommonTask.waitForElementpresent(driver, createPageBtn)) {
			driver.findElement(createPageBtn).click();
			Thread.sleep(4000);
			String mainWindoID = CommonTask.handlePopUps(driver);
			Thread.sleep(2000);
			return new CreatePage(driver);
		} else {
			System.out.println("Create Page button not found");
			return null;
		}

	}

	public CreatePage createNewPage() throws InterruptedException {
		return (this.click_createPageBtn());
	}

	public CreateBlankSpacePage click_createBlankSpaceBtn() throws InterruptedException {

		if (CommonTask.waitForElementpresent(driver, createSpaceLink)) {
			driver.findElement(createSpaceLink).click();
			Thread.sleep(4000);
			String mainWindoID = CommonTask.handlePopUps(driver);
			Thread.sleep(2000);
			return new CreateBlankSpacePage(driver);
		} else {
			System.out.println("Create a Space button not found");
			return null;
		}

	}

	public CreateBlankSpacePage createNewABlanckSpace() throws InterruptedException {
		return (this.click_createBlankSpaceBtn());
	}

	public SpaceDirectoryPage clickSpacesLbl() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, SpacesLbl)) {
			driver.findElement(SpacesLbl).click();
			Thread.sleep(2000);
			return new SpaceDirectoryPage(driver);
		} else {
			System.out.println("Spaces Label link not found");
			return null;
		}

	}
	
	public HomePage clickHomelogo() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, homeLogo)) {
			driver.findElement(homeLogo).click();
			Thread.sleep(3000);
			return new HomePage(driver);
		} else {
			System.out.println("Home button is not found");
			return null;
		}
	}

	public LogOutPage logOut() throws InterruptedException {

		if (verifiyProfileNsettingimg() && verifyActivityHeaderLbl())
			driver.findElement(profileNsettingimg).click();

		Thread.sleep(6000);
		driver.findElement(logOutLink).click();
		return new LogOutPage(driver);

		// return null;
	}
}
