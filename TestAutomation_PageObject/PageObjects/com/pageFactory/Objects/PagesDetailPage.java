package com.pageFactory.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.test.utils.CommonTask;

public class PagesDetailPage {

	WebDriver driver;

	By pagetitle = By.id("title-text");
	By pageNavigationLbl = By.xpath("//span[@class='kOItUc']/span[@class='hAotrp']/span[contains(text(),'Pages')]");

	By homeLogo = By.id("logo");

	/*
	 * By restictBtn = By
	 * .xpath("//a[@class='aui-icon aui-icon-small aui-iconfont-unlocked system-metadata-restrictions']"
	 * );
	 */

	By restictBtn = By.xpath("//div[@id='system-content-items']");
	By restrictionHeaderLbl = By.xpath("//h2[contains(text(),'Restrictions')]");
	By restrictoptionDropDown = By.id("page-restrictions-dialog-selector");
	By restrictSearchUserGroup = By.className("select2-input select2-default");
	By restrictPopUpAddBtn = By.id("page-restrictions-add-button");
	By restirctPopUpApplyBtn = By.id("page-restrictions-dialog-save-button");
	By restrictPopUpCancelLnk = By.id("page-restrictions-dialog-close-button");
	By restrictPopUpViewEditDropDown = By.id("page-restrictions-permission-selector");

	By restictionDescrition = By.className("page-restrictions-dialog-explanation");

	By restrictSelectionArrow = By.xpath("//span[@class='select2-arrow']");
	By restictNoRestriction = By.xpath(
			"//div[@class='restrictions-dialog-option']/span[@class='title' and contains(text(),'No restrictions')]");
	By restictEditingRestriction = By.xpath(
			"//div[@class='restrictions-dialog-option']/span[@class='title' and contains(text(),'Editing restricted')]");
	By restictViewNEditRestriction = By.xpath(
			"//div[@class='restrictions-dialog-option']/span[@class='title' and contains(text(),'Viewing and editing restricted')]");

	public PagesDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	private String getPageTitle() {

		if (CommonTask.waitForElementpresent(driver, pagetitle)) {
			String pageName = driver.findElement(pagetitle).getText();
			return pageName;
		} else
			return null;

	}

	private boolean verifyPageName(String expectedName) {
		return getPageTitle().contains(expectedName);
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

	public boolean verifyPageDetails(String pageName) throws InterruptedException {
		Thread.sleep(3000);
		if (CommonTask.waitForElementpresent(driver, pageNavigationLbl) && verifyPageName(pageName)) {
			return true;
		} else {
			System.out.println("Page name is not coreect");
			return false;
		}
	}

	private void clickRestrictionCancelButton() throws InterruptedException {
		if (CommonTask.waitForElementpresent(driver, restrictPopUpCancelLnk)) {
			driver.findElement(restrictPopUpCancelLnk).click();
			Thread.sleep(3000);
		}
	}

	private void clickRestrictionApplyButton() {
		if (CommonTask.waitForElementpresent(driver, restirctPopUpApplyBtn))
			driver.findElement(restirctPopUpApplyBtn).click();
	}

	private boolean clickRestrictButton() throws InterruptedException {

		if (CommonTask.waitForElementpresent(driver, restictBtn)) {
			driver.findElement(restictBtn).click();
			Thread.sleep(2000);
			CommonTask.handlePopUps(driver);
			if (verifyRestrictionPopUp())
				return true;
			else
				return false;
		} else
			return false;
	}

	private void clickRestrictionSelectionArrow() {
		if (CommonTask.waitForElementpresent(driver, restrictSelectionArrow))
			driver.findElement(restrictSelectionArrow).click();
	}

	private boolean verifyRestrictionPopUp() {

		if (CommonTask.waitForElementpresent(driver, restrictionHeaderLbl)
				&& CommonTask.waitForElementpresent(driver, restrictPopUpCancelLnk)) {
			System.out.println("On Restrictions Pop up");
			return true;
		} else
			return false;
	}

	private void settingNoRestrictions() throws InterruptedException {
		if (getRestricationDescription() != 1) {
			this.clickRestrictionSelectionArrow();
			Thread.sleep(2000);
			if (CommonTask.waitForElementpresent(driver, restictNoRestriction)) {
				driver.findElement(restictNoRestriction).click();
				this.clickRestrictionApplyButton();
				Thread.sleep(2000);
			} else
				System.out.println("Restriction option: No Restriction is not found");
		} else {
			System.out.println("Restriction Opetion: No Restriction option selected. Closing pop up");
			this.clickRestrictionCancelButton();
			Thread.sleep(3000);
		}
	}

	private void settingEditingRestrictions() throws InterruptedException {
		if (getRestricationDescription() != 2) {
			this.clickRestrictionSelectionArrow();
			if (CommonTask.waitForElementpresent(driver, restictEditingRestriction)) {
				driver.findElement(restictEditingRestriction).click();
				this.clickRestrictionApplyButton();
				Thread.sleep(2000);
			} else
				System.out.println("Restriction option: Editing Restrictions is not found");
		} else {
			System.out.println("Restriction Opetion: Editing option selected. Closing pop up");
			this.clickRestrictionCancelButton();
			Thread.sleep(3000);
		}

	}

	private void settingViewAndEditRestrictions() throws InterruptedException {
		if (getRestricationDescription() != 3) {
			this.clickRestrictionSelectionArrow();
			Thread.sleep(2000);
			if (CommonTask.waitForElementpresent(driver, restictViewNEditRestriction)) {
				driver.findElement(restictViewNEditRestriction).click();
				this.clickRestrictionApplyButton();
				Thread.sleep(2000);
			} else
				System.out.println("Restriction option: View and Editing Restrictions is not found");
		} else {
			System.out.println("Restriction Opetion: Viewing and editing option selected. Closing pop up");
			this.clickRestrictionCancelButton();
			Thread.sleep(3000);
		}
	}

	private int getRestricationDescription() {
		String noRestrictions = "Everyone can view and edit this page.";
		String editingRestrictions = "Everyone can view, only some can edit.";
		String viewNEditRsetrictions = "Only some people can view or edit.";

		String currentRestriction = driver.findElement(restictionDescrition).getText();
		if (currentRestriction.equals(noRestrictions)) {
			return 1;
		} else if (currentRestriction.equals(editingRestrictions)) {
			return 2;
		} else
			return 3;
	}

	public HomePage clickToSelectRestrictionType(String restrictionOption) throws InterruptedException {

		this.clickRestrictButton();
		Thread.sleep(2000);
		if (CommonTask.waitForElementpresent(driver, restrictoptionDropDown)) {
			Thread.sleep(2000);
			switch (restrictionOption.toLowerCase()) {
			case "no restrictions":
				this.settingNoRestrictions();
				break;
			case "editing restricted":
				this.settingEditingRestrictions();
				break;
			case "viewing and editing restricted":
				this.settingViewAndEditRestrictions();
				break;
			default:
				new IllegalAccessException("Restriction option is incorrect");
			}
			return this.clickHomelogo();
		} else {
			System.out.println("Restriction pop out not found");
			return null;
		}
	}
}
