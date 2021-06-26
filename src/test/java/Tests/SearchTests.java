package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search by substring")
    @Description("We search 'Java' article and make sure the result is expected")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search cancel button")
    @Description("We init search and than search cancel button")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Count amount elements after search input")
    @Description("We init search Linkin Park Diskography and than count amount elements")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)

    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Count amount elements for empty search input")
    @Description("We init search xadxsdxsd and than count amount elements")
    @Step("Starting test testAmountOfEmptySearch")
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "xadxsdxsd";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNotResultOfSearch();

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search init input and clean")
    @Description("We init search Java and than clear search input")
    @Step("Starting test testEX3CancelSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testEX3CancelSearch ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForSearchResult("Programming language");
        SearchPageObject.waitForTextInSearchAndClear();
        SearchPageObject.waitForSearchBoxIsEmpty("Programming language");

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check name of input search")
    @Description("We init search and check the name of input search")
    @Step("Starting test testEX2CheckSearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testEX2CheckSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.checkSearch("Search…");

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check worlds in titles")
    @Description("We init search and check worlds in titles")
    @Step("Starting test testEX4CheckWorldsInTitles")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testEX4CheckWorldsInTitles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkMultipleTitleForText("Java");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check 3 title after search")
    @Description("We init search Java and check 3 titles")
    @Step("Starting test testEX4CheckWorldsInTitles")
    @Severity(value = SeverityLevel.MINOR)
    public void testEX9RefactoringTPL ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "Results of search less than 3",
                amount_of_search_results > 2
        );
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");


    }

}
