package com.pageFactory.Actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Test;

import com.pageFactory.Objects.HomePage;
import com.pageFactory.Objects.LogOutPage;
import com.pageFactory.Objects.LoginPage;
import com.test.utils.BaseSeleniumTest;

public class LogOut_Test extends BaseSeleniumTest {
	
	@Test(dataProvider = "datatest")
	public void logOut(Map data) throws Exception {
		 
		System.out.println("In LogOut Method");
		
		HomePage homePage= new HomePage(super.driver);
		assertTrue((homePage.verifiyProfileNsettingimg() && homePage.verifyActivityHeaderLbl()));
		LogOutPage logOutPage=homePage.logOut();
		
		assertTrue(logOutPage.verifyLogOutBtn()&& logOutPage.verifyLogOutHeaderLbl());
		LoginPage loginPage=logOutPage.clicklogOutBtn();
		Thread.sleep(2000);
		assertEquals(loginPage.verifyLogInPageTitle(), true);
		System.out.println("User is successfully Logged Out");
	}
}
