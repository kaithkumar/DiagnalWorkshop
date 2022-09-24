/**
 * 
 */
package pages;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;


public class SearchPage {
	public static final By searchInputBox = MobileBy.xpath("//*[@resource-id='com.amazon.avod.thirdpartyclient:id/find_search_box_input']");
	public static final By searchResults = MobileBy.xpath("//*[@resource-id='com.amazon.avod.thirdpartyclient:id/title_card_primary_text']");
	public static final By watchFromBeginnigButton =MobileBy.xpath("//*[@text='Watch From Beginning']");
	public static final By continueWatchingButton = MobileBy.xpath("//*[@text='Continue watching']");
	public static final By videoTotalTimeDisplayer = MobileBy.xpath("//*[@resource-id='com.amazon.avod.thirdpartyclient:id/VideoTimeTotal']");

	
}
