package com.pageFactory.Actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pageFactory.Objects.HomePage;
import com.pageFactory.Objects.LoginPage;
import com.test.utils.BaseSeleniumTest;

public class LoginPage_Test extends BaseSeleniumTest {

	@Test(dataProvider = "datatest")
	public void signIn(Map data) throws Exception {

		System.out.println("In SigIn Method");
		String userName = (String) data.get("username");
		String password = (String) data.get("password");
		String isSuccessfull = (String) data.get("isSuccessfullLogin");
		
		LoginPage loginPage = new LoginPage(super.driver);
		assertEquals(loginPage.verifyLogInPageTitle(), true);
		HomePage homePage = loginPage.loginToApplication(userName, password);
		if (isSuccessfull.equalsIgnoreCase("true")) {			
			assertTrue((homePage.verifiyProfileNsettingimg() && homePage.verifyActivityHeaderLbl()));
		}
		else {
			assertEquals(loginPage.verifyLogInPageAfterLogOut(), true);
			System.out.println("User not able to login with wrong crednetials");
		}

		System.out.println("out of SigIn Method");
	}
}
