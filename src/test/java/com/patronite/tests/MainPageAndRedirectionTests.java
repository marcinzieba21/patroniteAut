package com.patronite.tests;

import com.patronite.helper.TestListener;
import com.patronite.pages.EarnPage;
import com.patronite.pages.LoginPage;
import com.patronite.pages.StartPage;
import com.patronite.pages.SupportPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)

public class MainPageAndRedirectionTests extends BaseSeleniumTest {

    String mainURL = "https://patronite.pl";

    @Test
    public void checkButtons() {

        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.checkHeaderButtons();
        startPage.clearStorage();
    }

    @Test(priority = 1)
    public void checkImages() {

        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.checkSizeOfImages();
        startPage.clearStorage();
    }


    @Test(priority = 2)
    public void checkImagesButtonsAndActivity() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.checkIfCardsAreActive();
        startPage.headerAssertion();
        startPage.clearStorage();
    }

    @Test(priority = 3)
    public void searchForRandomData() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.clickButtonSearch();
        startPage.verifyInputSearch();
        startPage.enterSomeDataInSearchInput();
        startPage.clearStorage();
    }

    @Test(priority = 4)
    public void redirectionToLoginPageCheck() {//this test is verifying redirection from main page, to login panel page

        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.clickButtonLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.elementsCheck();
        startPage.clearStorage();

    }

    @Test(priority = 5)
    public void supportButtonClick() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.clickButtonSupport();

        SupportPage supportPage = new SupportPage(driver);
        supportPage.headerCheck();
        startPage.clearStorage();
    }

    @Test(priority = 6)
    public void earnButtonClick() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.clickButtonEarn();

        EarnPage earnPage = new EarnPage(driver);
        earnPage.headerCheck();
        startPage.clearStorage();
    }

    @Test(priority = 7)
    public void menuButtonClick() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.clickButtonMenu();
        startPage.verifyMenu();
        startPage.clearStorage();
    }

    @Test(priority = 8)
    public void menuButtonClickAndSelectOption() {
        driver.get(mainURL);
        StartPage startPage = new StartPage(driver);
        startPage.acceptCookies();
        startPage.goToLoginUsingMenu();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.elementsCheck();
        startPage.clearStorage();
    }

}