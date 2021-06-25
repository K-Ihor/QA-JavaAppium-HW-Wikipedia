package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Assert element")
    @Description("Assert article with expected result")
    @Step("Start test_assertElementPresent")
    @Severity(value = SeverityLevel.MINOR)
    public void test_assertElementPresent() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_article = ArticlePageObject.getArticleTitle();
//        ArticlePageObject.takeScreenshot("article_page");
        Assert.assertTrue("title_article != 'Java (programming language)'", title_article.contains("Java (programming language)"));
    }
}
