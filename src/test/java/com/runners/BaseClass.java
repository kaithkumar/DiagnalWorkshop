package com.runners;
/**
 * 
 */


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.offset.PointOption;


public class BaseClass {
	private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
	private static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();
	
	public void startAppiumDriver(Platform type, LaunchType lauchType) 
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		AppiumDriver<MobileElement> driver = null;

		if(type.equals(Platform.Android))
		{
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPropertyValue("PlatformVersion"));
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			// chrome driver executable version used =105.0.5195.136
			caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriverForPoco.exe");
		    caps.setCapability(MobileCapabilityType.UDID, getPropertyValue("udid"));
			if(lauchType.equals(LaunchType.Install))
				caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"\\src\\test\\resources\\Application\\Prime Video.apk");
			if(lauchType.equals(LaunchType.Invoke))
			{
				caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getPropertyValue("appPackage"));
				caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getPropertyValue("appActivity"));
			}
			
			driver = new AndroidDriver<MobileElement>(getServer().getUrl(),caps);
			this.driver.set(driver);
		}
	   
	}
	
	
    public AppiumDriverLocalService WindowsGetAppiumService() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
    }
    

    
    public void startServer(){
        AppiumDriverLocalService server = WindowsGetAppiumService();
        server.start();
        if(server == null || !server.isRunning()){
            throw new AppiumServerHasNotBeenStartedLocallyException("server not started");
        }
        this.server.set(server);
    }
    
    public AppiumDriverLocalService getServer(){
        return server.get();
    }
    
    public AppiumDriver<MobileElement> getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver<MobileElement> driver2){
        driver.set(driver2);
    }
	public String getPropertyValue(String key) {
		String property="";
		try {
			Properties p = new Properties();
			File f= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\GlobalData.properties");
			p.load(new FileInputStream(f));
			property = p.getProperty(key);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return property; 
	}
	


	
	public String getTextFromElement(AppiumDriver<MobileElement> driver,By ele) {
		String trim = driver.findElement(ele).getText().trim();
		return trim;
	}
	public void waitForElementToBeInvisible(AppiumDriver<MobileElement> driver,By loader, int seconds) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, seconds);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void waitAndClick(AppiumDriver<MobileElement> driver, By ele)
	{
			try {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public void sendKeys(AppiumDriver<MobileElement> driver, By ele, String value)
	{
			try {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				MobileElement until = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(ele));
				until.click();
				until.clear();
				until.sendKeys(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void pressEnterInkeyBoard(AppiumDriver<MobileElement> driver)
	{
			try {
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void switchToWebView(AppiumDriver<MobileElement> driver) {
		Set<String> contextHandles = driver.getContextHandles();
		for (String string : contextHandles) {
			System.out.println(string);
			if(string.toLowerCase().contains("web"))
			{
				getDriver().context(string);
			}
		}
	}


	public boolean isElementDisplayed(AppiumDriver<MobileElement> driver, By ele, int timeLimit) {
		boolean check=false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			check=true;
		} catch (TimeoutException e) {
			check=false;
		}
		return check;
	}
	
	public int getSize(AppiumDriver<MobileElement> driver, By ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ele));
		List<MobileElement> findElements = driver.findElements(ele);
		return findElements.size();
		
	}
	
	public ArrayList<String>getTextList(AppiumDriver<MobileElement> driver, By ele)
	{
		ArrayList<String> li = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> until = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ele));
		for (WebElement webElement : until) {
			String text = webElement.getText();
			li.add(text);
		}
		return li;
	}
	
	public void tapWithCoordinates(AppiumDriver<MobileElement> driver, int xOffset, int yOffset)
	{
		TouchAction ta = new TouchAction(driver);
		ta.tap(PointOption.point(xOffset,yOffset)).perform();
	}

}
