package com.pageFactory.Actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Test;

import com.pageFactory.Objects.BlankPage;
import com.pageFactory.Objects.CreatePage;
import com.pageFactory.Objects.HomePage;
import com.pageFactory.Objects.PagesDetailPage;
import com.test.utils.BaseSeleniumTest;

public class CreatePage_Test extends BaseSeleniumTest {

	@Test(dataProvider = "datatest")
	public void createPage(Map data) throws Exception {

		String pageType = (String) data.get("pageType");
		String pageName = (String) data.get("pageName");
		String spaceName = (String) data.get("spaceName");
		String pageAction = (String) data.get("pageAction");

		System.out.println("In Create Page Method");

		HomePage homePage = new HomePage(super.driver);
		CreatePage createPage = homePage.createNewPage();
		assertEquals(createPage.verifyCreatePopUpLabel(), true);
		BlankPage blankPage = (BlankPage) createPage.createpage(spaceName, pageType);
		assertEquals(blankPage.verifyBlankPageTitle(), true);
		//blankPage.p
		PagesDetailPage pageDetailsPage = blankPage.enterDetailsInPage(pageName, pageAction);
		Thread.sleep(3000);
		assertEquals(pageDetailsPage.verifyPageDetails(pageName), true);
		pageDetailsPage.clickHomelogo();
		Thread.sleep(3000);
	}
}
