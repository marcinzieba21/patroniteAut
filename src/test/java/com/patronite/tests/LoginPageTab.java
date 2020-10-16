package com.patronite.tests;

import com.patronite.helper.TestListener;
import com.patronite.pages.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)

public class LoginPageTab extends BaseSeleniumTest {

    String loginURL = "https://patronite.pl/login";

    @Test
    public void checkButtonsLoginPanel() {//this test is verifying elements activity

        driver.get(loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.elementsCheck();
        loginPage.clearStorage();

    }


    @Test(priority = 1)
    public void loginButtonClickNoDataEntered() { //this test is verifying that, when there is no any data entered, an error notification will appear

        driver.get(loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.loginButtonClick();
        loginPage.verifyAlertErrorNoData();
        loginPage.clearStorage();
    }

    @Test(priority = 2)
    public void loginButtonClickWithRandomData() {//this test is verifying that, there is validation, for not existing user

        driver.get(loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.enterRandomEmail();
        loginPage.enterRandomPassword();
        loginPage.loginButtonClick();
        loginPage.verifyAlertErrorInvalidData();
        loginPage.clearStorage();
    }


}
