package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Assert assert")
    @Description("Assert text in search field")
    @Step("Start testCheck_text")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheck_text()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertElementHesText("Search…", "Search…");

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel Search")
    @Description("Check Cancel Search")
    @Step("Start testCheckAndCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckAndCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertElementHesText("Java version history", "Java version history");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertElementHesText("Search…", "Search…");
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Checking words in search")
    @Description("Checking words in search ")
    @Step("Start testCheckingWordsInSearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckingWordsInSearch()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.getAmountOfFoundArticle();
        SearchPageObject.CheckWordInSearchResult("Java");
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Search Result")
    @Description("Checking Search Result ")
    @Step("Start testCheckSearchResult")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckSearchResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();
        Assert.assertTrue("Search result < 3 article", amount_of_search_results >= 3);
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "rogramming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "bject-oriented programming language");

    }
}
