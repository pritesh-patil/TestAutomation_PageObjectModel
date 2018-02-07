package com.pageFactory.Objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpaceDirectoryPage {
	
	WebDriver driver;
	
	By spaceDirectoryHeader= By.id("title-text");
	By siteSpacesLbl= By.xpath("//div[@id='space-search-title-bar']/h2[contains(text(),'Site Spaces')]");
	By spacesList= By.xpath("//td[@class='entity-attribute space-name']/a");
	
	
	
	public SpaceDirectoryPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	private List<String> getSpaceLists(){
		
		List<WebElement> spaceLists= driver.findElements(spacesList);
		List<String> spaceListsName= new ArrayList<String>();
		
		for (WebElement e : spaceLists) {
			spaceListsName.add(e.getText().toString());
		}

		return spaceListsName;
	}
	
	public SpaceOverviewPage clickSpaceName(String spaceName) throws InterruptedException {
		List<String> spaceListsName=getSpaceLists();
		Iterator<String> spaces=spaceListsName.iterator();
		String tempSpaceName=null;
		while(spaces.hasNext()) {
			tempSpaceName=spaces.next();
			
			if(tempSpaceName.equals(spaceName)) {
				System.out.println(tempSpaceName);
				driver.findElement(By.linkText(tempSpaceName)).click();
				Thread.sleep(3000);
				return new SpaceOverviewPage(driver);
			}
		}
		return null;	
	}
}
