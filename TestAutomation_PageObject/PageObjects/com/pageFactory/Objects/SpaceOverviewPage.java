package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.utils.CommonTask;

public class SpaceOverviewPage {

	WebDriver driver;

	By overViewHeader = By.id("title-text");
	By pageLink = By.xpath("//span[@class='hAotrp']/span[contains(text(),'Pages')]");
	By blogLink = By.xpath("//span[@class='hAotrp']/span[contains(text(),'Blog')]");
	By overViewLink = By.xpath("//span[@class='hAotrp']/span[contains(text(),'Overview')]");

	By homeLogo = By.id("logo");

	public SpaceOverviewPage(WebDriver driver) {
		this.driver = driver;
	}

	public PagesPage clickToPagesLink() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, pageLink)) {
			driver.findElement(pageLink).click();
			Thread.sleep(3000);
			return new PagesPage(driver);
		} else {
			System.out.println("Spaces page tab is not loaded");
			return null;
		}
	}

	public PagesPage clickToBlogLink() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, blogLink)) {
			driver.findElement(blogLink).click();
			Thread.sleep(3000);
			return new PagesPage(driver);
		} else {
			System.out.println("Blog overview tab is not loaded");
			return null;
		}
	}

	public PagesPage clickToOverviewLink() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, overViewLink)) {
			driver.findElement(overViewLink).click();
			Thread.sleep(3000);
			return new PagesPage(driver);
		} else {
			System.out.println("Spaces overview tab is not loaded");
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
	private String getTitleHeader() {

		if (CommonTask.waitForElementpresent(driver, overViewHeader))
			return driver.findElement(overViewHeader).getText();
		else {
			System.out.println("Space Overview page tile is not loaded");
			return null;
		}

	}

	public boolean verifyPagesPageHeader(String spaceName) {
		if (getTitleHeader().equals(spaceName))
			return true;
		else
			return false;
	}

}
