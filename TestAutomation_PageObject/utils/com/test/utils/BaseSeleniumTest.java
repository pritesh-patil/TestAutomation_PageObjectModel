package com.test.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class BaseSeleniumTest {

	protected static WebDriver driver;

	private String baseURL;
	private String browser;
	private String env;

	private DesiredCapabilities cap;

	@BeforeSuite(alwaysRun = true)
	public void setUp(ITestContext context) {

		try {
			System.out.println("Setup");
			Properties prop = new Properties();
			InputStream input = null;

			input = new FileInputStream("./input.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			baseURL = prop.getProperty("BaseURL");
			browser = prop.getProperty("Browser");
			env = prop.getProperty("Environment");
			System.out.println("Base URL :" + baseURL);

			switch (browser.toLowerCase()) {
			case "chrome":
				String driverPath = null;
				if (env.equalsIgnoreCase("mac")) {
					cap = DesiredCapabilities.chrome();
					// cap.setVersion("55.0.2");
					// cap.setBrowserName("chrome");
					cap.setPlatform(org.openqa.selenium.Platform.MAC);
					driverPath = "./Drivers/chromedriver";
				} else
					driverPath = ".\\Drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				Thread.sleep(5000);
				driver.get(baseURL);
				break;
			case "firefox":
				try {
					System.out.println("FireFox");
					System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
					driver.get(baseURL);
				} catch (Exception e) {
					throw new IllegalStateException("Can't start Web Driver", e);
				}
				break;
			default:
				throw new IllegalArgumentException("Brwoser type is incorrect");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "datatest")
	public Object[][] dataprovider(ITestContext context) {
		Map<String, String> map = null;
		System.out.println("In dataprovider");
		try {

			// XmlTest xml = context.getCurrentXmlTest();
			String suiteName = context.getCurrentXmlTest().getSuite().getName();
			String fileName = context.getCurrentXmlTest().getSuite().getFileName();
			String testName = context.getName();
			map = XmlDataMapper.getDataFromXML(fileName, testName);
			System.out.println(suiteName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object[][] { { map } };

	}
}
