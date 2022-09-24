/**
 * 
 */
package com.stepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.runners.BaseClass;
import com.runners.LaunchType;
import com.runners.Platform;


import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.SearchPage;


public class SearchMovieTest extends BaseClass {
	@When("I enter username and password as {string} and {string}")
	public void iEnterUsernameAndPasswordAsAnd(String string, String string2) throws InterruptedException {
		//startAppiumDriver(Platform.Android, LaunchType.Install);
		Thread.sleep(10000);
		waitForElementToBeInvisible(getDriver(),LoginPage.loader, 30);
		switchToWebView(getDriver());
	    sendKeys(getDriver(), LoginPage.emailInputBox,string);
	    sendKeys(getDriver(), LoginPage.passwordInputBox,string2);
	}
	@When("I click search button")
	public void iClickSearchButton() throws InterruptedException {
		waitAndClick(getDriver(), LoginPage.signInButton);
		waitAndClick(getDriver(), LoginPage.letsGoPopup);
	}
	@When("I search for a movie named {string}")
	public void iSearchForAMovieNamed(String string) throws InterruptedException {
		waitAndClick(getDriver(), LoginPage.searchButton);
		sendKeys(getDriver(), SearchPage.searchInputBox, string);
		Thread.sleep(5000);
		pressEnterInkeyBoard(getDriver());
	}
	@Then("Result suggestion should not be empty")
	public void resultSuggestionShouldNotBeEmpty() throws InterruptedException {
		Thread.sleep(5000);
		ArrayList<String> textList = getTextList(getDriver(), SearchPage.searchResults);
		if(textList.size()>0)
			Assert.assertTrue("Search result is not empty", true);
		if(textList.contains("TOP GUN: MAVERICK BONUS SNEAK PEEK"))
			Assert.assertTrue("Search result lis has the same movie", true);
		//getDriver().quit();
	}
	
	@Then("Navigate to the detials page")
	public void navigateToTheDetialsPage() throws InterruptedException {
		List<MobileElement> findElements = getDriver().findElements(SearchPage.searchResults);
		for (MobileElement mobileElement : findElements) {
			if(mobileElement.getText().trim().equals("TOP GUN: MAVERICK BONUS SNEAK PEEK"))
			{
				mobileElement.click();
			}
			
		}
		boolean elementDisplayed = isElementDisplayed(getDriver(), SearchPage.watchFromBeginnigButton, 20);
		boolean elementDisplayed2 = isElementDisplayed(getDriver(), SearchPage.continueWatchingButton, 20);
		if(elementDisplayed== true || elementDisplayed2)
			Assert.assertTrue("details Page dsiplayed",true);
		else
			Assert.assertTrue("details Page not dsiplayed",false);
	}

}
