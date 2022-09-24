/**
 * 
 */
package com.stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.Dimension;


import com.runners.BaseClass;

import io.cucumber.java.en.Then;
import pages.SearchPage;


public class PlayVideoTest extends BaseClass{
	
	@Then("I click play the Video")
	public void iClickPlayTheVideo() throws InterruptedException {
		boolean elementDisplayed = isElementDisplayed(getDriver(), SearchPage.watchFromBeginnigButton, 20);
		if (elementDisplayed == true)
			waitAndClick(getDriver(), SearchPage.watchFromBeginnigButton);
		else
			waitAndClick(getDriver(), SearchPage.continueWatchingButton);
		Thread.sleep(10000);
	}
	@Then("Video should be playable")
	public void videoShouldBePlayable() throws InterruptedException {
		Dimension size = getDriver().manage().window().getSize();
		tapWithCoordinates(getDriver(), (size.width / 2), (size.height / 2));
		String timeDuringVideoStart = getTextFromElement(getDriver(), SearchPage.videoTotalTimeDisplayer);
		Thread.sleep(10000);
		tapWithCoordinates(getDriver(), (size.width / 2), (size.height / 2));
		String timeDuringVideoEnd = getTextFromElement(getDriver(), SearchPage.videoTotalTimeDisplayer);
		if(!timeDuringVideoStart.equals(timeDuringVideoEnd))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}

}
