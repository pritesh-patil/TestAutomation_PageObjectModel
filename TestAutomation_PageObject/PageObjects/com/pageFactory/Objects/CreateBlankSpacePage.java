package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.utils.CommonTask;

public class CreateBlankSpacePage {

	WebDriver driver;

	By createSpaceLink = By.id("addSpaceLink");
	By createSpacePopUpLbl = By.xpath("//h2[contains(text(),'Create space')]");
	By createABlankSpacePopUpLbl = By.xpath("//h2[contains(text(),'Create a blank space')]");

	By nextBtnPopUp = By.xpath("//button[contains(text(),'Next')]");
	By closeLinkPopUp = By.className("button-panel-link button-panel-cancel-link");
	By SpaceType_BlankPage = By.xpath("//li/div/div[contains(text(),'Blank space')]");
	By spaceName = By.xpath("//label[contains(text(),'Space name')]/following-sibling::input[@name='name']");
	By spaceKey = By.xpath("//label[contains(text(),'Space key')]/following-sibling::input[@name='spaceKey']");
	By spaceCreateBtn = By.xpath("//div[@class='dialog-button-panel']/button[contains(text(),'Create')]");

	public CreateBlankSpacePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyInitialPopUpHeader() {
		if (CommonTask.waitForElementpresent(driver, createSpacePopUpLbl)) {
			return true;
		} else {
			System.out.println("Pop Up header is not found");
			return false;
		}
	}

	public boolean verifyNextPagePopUpHeader() {
		if (CommonTask.waitForElementpresent(driver, createABlankSpacePopUpLbl)) {
			return true;
		} else {
			System.out.println("Pop Up second page header is not found");
			return false;
		}
	}

	private void clickToCreateBlankSpace() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, createSpaceLink)) {
			driver.findElement(createSpaceLink).click();
			Thread.sleep(3000);
			CommonTask.handlePopUps(driver);
			Thread.sleep(3000);
		} else {
			System.out.println("Create Space Button Not found");
		}
	}

	private void clickToSelectBlankSpace() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, SpaceType_BlankPage)) {
			driver.findElement(SpaceType_BlankPage).click();
			Thread.sleep(2000);
		} else {
			System.out.println("Create Blank Space option  Not found");
		}
	}

	private boolean clickToNextBtnPopUp() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, nextBtnPopUp)) {
			driver.findElement(nextBtnPopUp).click();
			Thread.sleep(2000);
			if (this.verifyNextPagePopUpHeader()) {
				return true;
			} else {
				System.out.println("Create a blank space pop up is not found");
				return false;
			}
		} else {
			System.out.println("Next button on Create Space pop up not found");
			return false;
		}
	}

	private void enterSpaceName(String Name) {
		if (CommonTask.waitForElementpresent(driver, spaceName)) {
			driver.findElement(spaceName).sendKeys(Name);
		} else {
			System.out.println("Space Name textbox not found");
		}
	}

	private void enterSpaceKey(String Key) throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, spaceKey)) {
			driver.findElement(spaceKey).clear();
			Thread.sleep(1000);
			driver.findElement(spaceKey).sendKeys(Key);
		} else {
			System.out.println("Space Name textbox not found");
		}
	}

	private void clickToCreateSpaceBtn() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, spaceCreateBtn)) {
			driver.findElement(spaceCreateBtn).click();
			Thread.sleep(2000);

		} else {
			System.out.println("Create space pop up not found");
		}
	}

	private void clickTocloseBtnPopUp() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, closeLinkPopUp)) {
			driver.findElement(closeLinkPopUp).click();
			Thread.sleep(2000);

		} else {
			System.out.println("Close link on Create space pop up not found");
		}
	}

	public SpaceOverviewPage createABlankSpace(String Name, String key) throws InterruptedException {
		// this.clickToCreateBlankSpace();
		if (this.verifyInitialPopUpHeader()) {
			this.clickToSelectBlankSpace();
			this.clickToNextBtnPopUp();
			Thread.sleep(2000);
			if (this.verifyNextPagePopUpHeader()) {
				this.enterSpaceName(Name);
				/*Thread.sleep(2000);
				this.enterSpaceKey(key);*/
				Thread.sleep(2000);
				this.clickToCreateSpaceBtn();
				return new SpaceOverviewPage(driver);
			} else {
				System.out.println("Next screenn of create space pop is not loaded");
			}
		} else {
			System.out.println("Initial screen of Create space pop is not loaded");
		}
		return null;
	}
}
