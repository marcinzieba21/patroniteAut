package com.patronite.pages;

import com.patronite.helper.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy (xpath = "//h4[text () =\"Zaloguj się\"]")
    private WebElement header;

    @FindBy (xpath = "//label[@for=\"remember_me\"]")
    private WebElement rememberMeCheckbox;

    @FindBy (xpath = "//input[@name=\"email\"]")
    private WebElement inputEmail;

    @FindBy (xpath = "//input[@name=\"password\"]")
    private WebElement inputPassword;

    @FindBy (xpath = "//input[@value=\"zaloguj\"]")
    private WebElement loginButton;

    @FindBy (xpath = "//a[@class=\"login--facebook\"]")
    private WebElement loginByFb;

    @FindBy (xpath = "//a[@href=\"https://patronite.pl/login/google\"]")
    private WebElement loginByGoogle;

    @FindBy (xpath = "//div[@class=\"alert alert-error col-xs-11 col-sm-8\"]")
    private WebElement alertError;

    @FindBy (xpath = "//div[@class=\"alert alert-error auth col-xs-11 col-sm-8\"]")
    private WebElement alertErrorNoData;

    @FindBy (xpath = "//button[@class=\"btn-submit OK\"]")
    private WebElement cookiesAccept;


    private SeleniumHelper helper;



    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);


}

    public void elementsCheck () {
        helper.getTextAssertion("header check", header, "Zaloguj się");
        helper.checkActivityElement("Checkbox check", rememberMeCheckbox, "Zapamiętaj mnie");
        helper.checkInputsActivity("input email check", inputEmail);
        helper.checkInputsActivity("input password check", inputPassword);
        helper.checkActivityElementValue("button login check", loginButton, "zaloguj");
        helper.checkActivityElement("fb login check", loginByFb, "ZALOGUJ SIĘ PRZEZ FACEBOOKA");
        helper.checkActivityElement("google login check", loginByGoogle, "ZALOGUJ SIĘ PRZEZ GOOGLE");
    }

    public void acceptCookies () {
        helper.elementClickWithAssert("accept cookies click", cookiesAccept, "OK");
    }

    public void loginButtonClick () {
        helper.elementClickWithAssertValue("login button click", loginButton, "zaloguj");
    }

    public void verifyAlertErrorNoData() {
        helper.getTextAssertion("Alert check", alertErrorNoData, "Nie udało się zalogować. Sprawdź, czy wprowadziłeś prawidłowe dane.\n" +
                "OK");
    }

    public void enterRandomPassword () {
        String enteringData = helper.randomAlphabetic(12);
        helper.enterDataInInput("entering email", inputPassword, enteringData);
    }

    public void enterRandomEmail () {
        String enteringData = helper.randomAlphabetic(7);
        String emailValid = "@gmail.com";
        helper.enterDataInInput("entering email", inputEmail, enteringData+emailValid);

    }

    public void verifyAlertErrorInvalidData() {
        helper.getTextAssertion("Alert check", alertError, "Podano niepoprawnie email i/lub hasło\n" +
                "OK");
    }

    public void clearStorage() {
        helper.clearLocalStorage();
    }


}