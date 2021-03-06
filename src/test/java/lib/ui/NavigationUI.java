package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{

    protected static String
    MY_LISTS_LINK,
    OPEN_NAVIGATION;

    public NavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }


    @Step("Click my Lists")
    public void clickMyLists () {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Can't find navigation button to my list", 5);
        } else {
            this.waitForElementAndClick(
                    (MY_LISTS_LINK),
                    "Can't find navigation button to my list",
                    5
            );
        }
    }
    @Step("Click open navigation button")
    public void openNavigation ()
    {
        if (Platform.getInstance().isMW())
        {
            this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click open navigation button.",5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }


}
