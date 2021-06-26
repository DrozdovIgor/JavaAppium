package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Welcome screen")})
    @DisplayName("Pass through welcome screen")
    @Description("We use this method only for iOS platform")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.BLOCKER)

    public void testPassThroughWelcome () {

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
