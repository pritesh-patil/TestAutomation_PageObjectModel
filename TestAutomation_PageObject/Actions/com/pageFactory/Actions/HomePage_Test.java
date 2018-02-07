package com.pageFactory.Actions;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.pageFactory.Objects.BlankPage;
import com.pageFactory.Objects.CreateBlankSpacePage;
import com.pageFactory.Objects.CreatePage;
import com.pageFactory.Objects.HomePage;
import com.pageFactory.Objects.PagesDetailPage;
import com.pageFactory.Objects.PagesPage;
import com.pageFactory.Objects.SpaceDirectoryPage;
import com.pageFactory.Objects.SpaceOverviewPage;
import com.test.utils.BaseSeleniumTest;

public class HomePage_Test extends BaseSeleniumTest {

	@Test(dataProvider = "datatest")
	public void addRestrictionToExistingPage(Map data) throws Exception {

		String spaceName = (String) data.get("spaceName");
		String pageName = (String) data.get("pageName");
		String restrictionOption = (String) data.get("RestrictionType");

		System.out.println("In add Restriction Method");

		HomePage homePage = new HomePage(super.driver);
		SpaceDirectoryPage spaceDirPage = homePage.clickSpacesLbl();
		Thread.sleep(2000);
		SpaceOverviewPage spaceOverView = spaceDirPage.clickSpaceName(spaceName);
		assertEquals(spaceOverView.verifyPagesPageHeader(spaceName), true);
		Thread.sleep(2000);
		PagesPage pagesPage = spaceOverView.clickToPagesLink();
		assertEquals(pagesPage.verifyPagesPageHeader(), true);
		Thread.sleep(2000);
		PagesDetailPage pageDetailPage = pagesPage.clickPageName(pageName);
		assertEquals(pageDetailPage.verifyPageDetails(pageName), true);
		Thread.sleep(2000);
		pageDetailPage.clickToSelectRestrictionType(restrictionOption);
		Thread.sleep(2000);
	}

	@Test(dataProvider = "datatest")
	public void createBlankSpace(Map data) throws Exception {

		String spaceName = (String) data.get("spaceName");
		String spaceKey = (String) data.get("spaceKey");

		System.out.println("In create A Blank Space  Method");

		HomePage homePage = new HomePage(super.driver);
		CreateBlankSpacePage blankSpace = homePage.createNewABlanckSpace();
		Thread.sleep(2000);
		SpaceOverviewPage spaceOverView = blankSpace.createABlankSpace(spaceName, spaceKey);
		Thread.sleep(3000);
		assertEquals(spaceOverView.verifyPagesPageHeader(spaceName), true);
		Thread.sleep(2000);
		spaceOverView.clickHomelogo();
	}
}
