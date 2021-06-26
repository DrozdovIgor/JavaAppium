package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder= "Learning programming";
    private static final String
    login = "drozdovtest",
    password = "drozdov2010";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="List")})
    @DisplayName("Save article to my list and delete after that")
    @Description("We save 'Java Object-oriented programming language' article to my list and delete it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject =  ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login.", article_title, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName (name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="List")})
    @DisplayName("Save two articles to my list and delete one after that")
    @Description("We save 'Java Object-oriented programming language' and 'Java JavaScript' article to my list and delete first article")
    @Step("Starting test testEX17SaveTwoArticles")
    @Severity(value = SeverityLevel.BLOCKER)

    public void testEX17SaveTwoArticles() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login.", article_title, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");


        ArticlePageObject.waitForTitleElement();
        String article_text_before_add = ArticlePageObject.getArticleText();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        if (Platform.getInstance().isMW())
        {
            NavigationUI.openNavigation();
        }
        NavigationUI.clickMyLists();


        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        String article_text_after_add = ArticlePageObject.getArticleText();

        Assert.assertEquals(
                "Article text have been change after add",
                article_text_before_add,
                article_text_after_add
        );


    }


    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="List")})
    @DisplayName("Save two articles to my list and delete one after that")
    @Description("Its only for iOS and Android. We save 'Java Object-oriented programming language' and 'Java JavaScript' article to my list and delete first article")
    @Step("Starting test testEX5SaveTwoArticles")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testEX5SaveTwoArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject =  ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder= "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject.waitForTitleElement();
        String article_title_before_add = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addMoreArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        String article_title_after_add = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after add",
                article_title_before_add,
                article_title_after_add
        );

    }

}

