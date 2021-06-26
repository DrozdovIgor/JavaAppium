package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for change app conditions")

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Compare article title after change screen orientation")
    @Description("We open 'Java Object-oriented programming language' article then change orientation and compare article")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults()
    {

        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Compare article title after background")
    @Description("We open 'Java Object-oriented programming language' article then collapse app into background ")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground ()
    {

        if (Platform.getInstance().isMW()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

}
