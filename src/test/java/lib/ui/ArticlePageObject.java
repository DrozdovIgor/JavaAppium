package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;


abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
    TITLE,
    TEXT,
    FOOTER_ELEMENT,
    OPTION_BUTTON,
    OPTION_ADD_TO_MY_LIST_BUTTON,
    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON;


    public ArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent((TITLE), "Cannot find article title on page", 15);
    }

    @Step("Waiting for text on the article page")
    public WebElement waitForTextElement ()
    {
        return this.waitForElementPresent((TEXT), "Cannot find part of text on page", 15);
    }


    @Step("Get article text")
    public String getArticleText () {

        WebElement text_element = waitForTextElement();
        if (Platform.getInstance().isAndroid()) {
            return text_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return text_element.getAttribute("name");
        } else {
            return text_element.getText();
        }
    }

    @Step("Get article title")
    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter ()
    {
        if (Platform.getInstance().isAndroid())
        {
            this.swipeUpToFindElement((FOOTER_ELEMENT), "Cannot find the end of article", 20);
        }
     else if (Platform.getInstance().isIOS()){
        this.swipeUpTillElementAppear((FOOTER_ELEMENT),"Cannot find the end of article",40);
         }
     else {
         this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,"Cannot find the end of Article",40);
        }

    }

    @Step("Adding the article to my list")
    public void addArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                (OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );


        this.waitForElementPresent ( //ожидание элемента
                (OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find article title",
                15
        );

        this.waitForElementAndClick(
                (OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Cannot find Got it tip overlay",
                10
        );

        this.waitForElementAndClear(
                (MY_LIST_NAME_INPUT),
                "Can't find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                (MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                (MY_LIST_OK_BUTTON),
                "Can't press OK Button",
                5
        );
    }

    @Step("Adding the article to my saved articles")
    public void addArticlesToMySaved ()
    {
        if (Platform.getInstance().isMW()) {
        this.removedArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    @Step("Removed the article from saved if it has been added")
    public void removedArticleFromSavedIfItAdded ()
    {
        if (this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON))
        {
            this.waitForElementAndClick(OPTION_REMOVE_FROM_MY_LIST_BUTTON,"Cannot click button to remove article from saved",1);
            this.waitForElementPresent(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot find button to add article to saved list after removing it from this list before",1);
        }
    }


    @Step("Closing the article")
    public void closeArticle ()
    {
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {

            this.waitForElementAndClick(
                    (CLOSE_ARTICLE_BUTTON),
                    "Can't close article, can't find X link",
                    5
            );
        }
            else {
            System.out.println("Method closeArticle() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Add article if article > 1")
    public void addMoreArticleToMyList (String name_of_folder)
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {


            this.waitForElementAndClick(
                    (OPTION_BUTTON),
                    "Cannot find button to open article options",
                    5
            );


            this.waitForElementPresent( //ожидание элемента
                    (OPTION_ADD_TO_MY_LIST_BUTTON),
                    "Cannot find article title",
                    15
            );

            this.waitForElementAndClick(
                    (OPTION_ADD_TO_MY_LIST_BUTTON),
                    "Cannot find option to add article to reading list",
                    5
            );

            this.waitForElementPresent(
                    ("xpath://*[@text='" + name_of_folder + "']"),
                    "Cannot find article title",
                    15
            );

            this.waitForElementAndClick( // добавили вторую статью в сохраненки
                    ("xpath://*[@text='" + name_of_folder + "']"),
                    "Cannot find option to add article to reading list",
                    5
            );
        }


    }
    @Step("Check article title")
    public void checkArticleTitle ()
    {
        this.assertElementPresent((TITLE),"Article has not title_element");
    }



}
