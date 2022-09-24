# DiagnalWorkshop 
Created on: 25/09/2022
Language used: Java 🍵
Tools used ⚒️⚙️: Maven, cucumber, java_client, selenium
Platform support : Developed on Windows 11🪟, Supports any platform with JVM

**This project holds the solution for the below problems:**
1. Verify whether Able to login.
2. Verify whether Able to search for a movie.
3. Verify whether Able to go to detail page from the search result.
4. Verify whether Able to play the video.
5. Create an extent test report for the same.


**Prerequesites to run the tests:**
1. provide udid of the device to be used for automation in the global data properties file.
2. update the desired capability "app" for the required app to be automated.
3. store the .apk (or) .app (or) .ipa file int the application store folder.
4. update the chrome driver version which matches with the mobiles chrome version.


**Difficulties faced:**
1. Need to ensure the amazon prime app is authenticated(two way authentication) which cannot be automated in production app.
2. unable take screenshot on failure, since the falg is set as secure(app cannot be screen mirrored, screen shot cannot be taken) for production app. 
3. unable to inspect element in appium inspector,since the falg is set as secure.(print the page source and build locator).


**Ways to run the tests:**
1. provide tags of the scenarios needed to be run in test runner class and run the junit test.

**Extent report folder:**
1. After the completion of test run, open the test-output folder to access Extent report and Pdf report.


