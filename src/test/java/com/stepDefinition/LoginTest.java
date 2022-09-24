/**
 * 
 */
package com.stepDefinition;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.runners.BaseClass;
import com.runners.LaunchType;
import com.runners.Platform;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginTest extends BaseClass {

	@When("I enter username as {string}")
	public void iEnterUsernameAs(String string) throws InterruptedException {
		Thread.sleep(10000);
		waitForElementToBeInvisible(getDriver(), LoginPage.loader, 30);
		switchToWebView(getDriver());
		sendKeys(getDriver(), LoginPage.emailInputBox, string);
	}

	@When("I enter password as {string}")
	public void iEnterPasswordAs(String string) {
		sendKeys(getDriver(), LoginPage.passwordInputBox, string);
	}

	@When("I login")
	public void iLogin() {
		waitAndClick(getDriver(), LoginPage.signInButton);
	}

	@Then("I should see amazon home page")
	public void iShouldSeeAmazonHomePage() throws InterruptedException {
		Thread.sleep(10000);
		waitAndClick(getDriver(), LoginPage.letsGoPopup);
		boolean elementDisplayed = isElementDisplayed(getDriver(), LoginPage.searchButton, 20);
		Assert.assertTrue("page login success", elementDisplayed);
	}
   
	
	// hooks 
	@Before
	public void fireUp() {
		startAppiumDriver(Platform.Android, LaunchType.Install);
	}

	@After(order = 1)
	public void teardown(Scenario s) throws IOException {
		if (s.isFailed()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) getDriver();
				ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(ts.getScreenshotAs(OutputType.FILE),
						new File(System.getProperty("user.dir") + "\\ScreenShot\\" + s.getName() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (getDriver() != null)
			getDriver().quit();
	}

	@After(order = 0)
	public void teardown() throws IOException {
		if (getDriver() != null)
			getDriver().quit();
	}

	@AfterStep
	public void attachingScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			byte[] screenshotAs = ts.getScreenshotAs(OutputType.BYTES);
			s.attach(screenshotAs, "image/png", "error screen");
		}

	}

}
