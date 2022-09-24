/**
 * 
 */
package pages;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;


public class LoginPage {

	public static final By loader = MobileBy.id("com.amazon.avod.thirdpartyclient:id/apimageview");
	public static final By emailInputBox = MobileBy.xpath("//*[@resource-id='ap_email']");
	public static final By passwordInputBox = MobileBy.xpath("//*[@resource-id='ap_password']");
	public static final By signInButton = MobileBy.xpath("//*[@text='Sign-In']");
	public static final By letsGoPopup = MobileBy.xpath("//*[@resource-id='com.amazon.avod.thirdpartyclient:id/action_button_text_two']");
	public static final By searchButton = MobileBy.xpath("//*[@text='Find']");
}
