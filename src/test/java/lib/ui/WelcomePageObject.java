package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEAR_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAY_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add to edit preferred language",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started",
    SKIP = "id:Skip";



    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
         this.waitForElementPresent((STEP_LEAR_MORE_LINK),
                 "Cannot find 'Learn more about Wikipedia' link",
                 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent((STEP_NEW_WAY_TO_EXPLORE),
                "Cannot find 'New ways to explore' link",
                10);
    }

    public void waitForAddOrEditPreferLangText()
    {
        this.waitForElementPresent((STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK),
                "Cannot find 'Add to edit preferred language' link",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent((STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK ),
                "Cannot find 'Learn more about data collected' link",
                10);
    }


    public void clickNextButton()
    {
        this.waitForElementAndClick((NEXT_LINK),
                "Cannot find and click 'Next' link",
                10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick((GET_STARTED_BUTTON),
                "Cannot find and click 'Get started' link",
                10);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP,"Cannot find click skip button",5);
    }

}
