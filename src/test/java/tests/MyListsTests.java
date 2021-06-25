package tests;

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

@Epic("Tests for save list")
public class MyListsTests extends CoreTestCase
{
    private static final String
            login = "K-Ihor-m",
            password = "KIhor324217";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Saving articles")
    @Description("save articles")
    @Step("Start test_save_of_2_articles")
    @Severity(value = SeverityLevel.NORMAL)
    public void test_save_of_2_articles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.createMyListAndAddArticle("Learning programing");
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitFrom();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java");

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList("Learning programing");
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName("Learning programing");
        }

        MyListsPageObject.swipeByArticleToDelete("Java");

        MyListsPageObject.waitForArticleToDisappearByTitle("Java");
        MyListsPageObject.waitForArticleToAppearByTitle("Java (programming language)");
        SearchPageObject.assertElementHesText("Java (programming language)", "Java (programming language)");
    }
}
