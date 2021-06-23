package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        TEXT = "xpath://*[@id='mf-section-0']//p//b[contains(text(),'Java')]";
        FOOTER_ELEMENT = "css:footer";
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch'][text()='Watch']";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@id='ca-watch'][text()='Unwatch']";
    }

    public MWArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}

