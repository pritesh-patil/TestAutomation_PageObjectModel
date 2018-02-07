package com.pageFactory.Objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.utils.CommonTask;

public class PagesPage {

	WebDriver driver;

	By pagesHeader = By.id("title-text");
	By pagesLists = By.className("link-title");

	public PagesPage(WebDriver driver) {
		this.driver = driver;
	}

	private String getTitleHeader() {
		if (CommonTask.waitForElementpresent(driver, pagesHeader)) {
			return driver.findElement(pagesHeader).getText();
		} else {
			System.out.println("Page is not loaded");
			return null;
		}
	}

	public boolean verifyPagesPageHeader() {
		String expectedHeader = "Pages";

		if (getTitleHeader().equals(expectedHeader))
			return true;
		else
			return false;
	}

	private List<String> getPagesLists() {

		List<WebElement> pagesList = driver.findElements(pagesLists);
		List<String> pagesName = new ArrayList<String>();

		for (WebElement e : pagesList) {
			pagesName.add(e.getText().toString());
		}

		return pagesName;
	}

	public PagesDetailPage clickPageName(String pageName) throws InterruptedException {
		List<String> PageListsName = getPagesLists();
		Iterator<String> pages = PageListsName.iterator();
		String tempPageName = null;
		while (pages.hasNext()) {
			tempPageName = pages.next();
			if (tempPageName.equals(pageName)) {
				driver.findElement(By.linkText(tempPageName)).click();
				Thread.sleep(3000);
				return new PagesDetailPage(driver);
			}
		}
		return null;

	}
}
