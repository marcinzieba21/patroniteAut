package com.patronite.pages;

import com.patronite.helper.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartPage {

    @FindBy (xpath = "(//a[@href=\"https://patronite.pl/jak_to_dziala#patron\"])[1]")
    private WebElement supportButton;

    @FindBy (xpath = "//a[@href=\"https://patronite.pl/jak_to_dziala#autor\" and text () =\"Zarabiaj\"]")
    private WebElement earnButton;

    @FindBy (xpath = "//div[@class=\"nav__list--element\"] [@id=\"searchToggle\"]")
    private WebElement searchButton;

    @FindBy (xpath = "//a[@href=\"https://patronite.pl/login\" and text () =\"Zaloguj\"]")
    private WebElement loginButton;

    @FindBy (xpath = "//a[@href=\"#\" and text () =\"Menu\"]")
    private WebElement menuButton;

    @FindBy (xpath = "//input[@placeholder=\"Wpisz nazwę autora, min. 3 znaki\"]")
    private WebElement searchInput;

    @FindBy (css = "body > div.menu__modal > div")
    private WebElement expandedMenu;

    @FindBy (xpath = "(//a[@href=\"https://patronite.pl/login\" and text () =\"Logowanie\"])[1]")
    private WebElement loginMenu;

    @FindBy (xpath = "//button[@class=\"btn-submit OK\"]")
    private WebElement cookiesAccept;

    private SeleniumHelper helper;

    private WebDriver driver;

    private
    List<String> menuElements = Arrays. asList("YouTube", "Blog", "Gry","Wyprawy","Nauka","Społeczne","#Kulturawsieci","Sport","Fotografia","Podcast","Zwierzęta","Film","Rękodzieło","Edukacja","Motoryzacja", "Publicystyka", "Komiks" , "Inne", "Logowanie", "Rejestracja","FAQ","Jak to działa","Baza wiedzy", "Kontakt z Patronite", "Regulamin");

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;

}


    public void checkHeaderButtons() {
        helper.checkDisplayingElement("check button wspieraj", supportButton, "Wspieraj");
        helper.checkActivityElement("check button zarabiaj", earnButton, "Zarabiaj");
        helper.isEnable(searchButton);
        helper.checkActivityElement("check button zaloguj", loginButton, "Zaloguj");
        helper.checkActivityElement("check button menu", menuButton, "Menu");
    }


    public void checkSizeOfImages (){ //lazy loaded img only
        List<WebElement> images = new ArrayList<>();
        for (int i = 1; i <=9 ; i++) {
            String xPath = "(//div[@class=\"author__card--image\"]/span/img[@class=\"flickity-lazyloaded\"])["+i+"]";
            images.add(driver.findElement(By.xpath(xPath)));
            helper.waitForListOfWebElements(images);
        }
        for (WebElement webElement : images) {
            helper.checkSizeImg(webElement, 370, 370);
        }
    }

    public void checkIfCardsAreActive () {// only top 4 cards
        List<WebElement> cards = new ArrayList<>();
        for (int i = 1; i <=4 ; i++) {
            String xPath = "(//div[@class=\"author__card--image\"])["+i+"]";
            cards.add(driver.findElement(By.xpath(xPath)));
            helper.waitForListOfWebElements(cards);
        }
        for (WebElement webElement : cards) {
            helper.isActive(webElement);
        }
    }

    public void clickButtonSupport () {
        helper.elementClickWithAssert("button support clicked", supportButton, "Wspieraj");
    }

    public void acceptCookies () {
        helper.elementClickWithAssert("accept cookies click", cookiesAccept, "OK");
    }

    public void clickButtonEarn () {
        helper.actionClick(earnButton, "button Earn clicked");
    }

    public void clickButtonSearch () {
        helper.actionClick(searchButton, "button clicked");
    }

    public void clickButtonLogin () {
        helper.elementClickNoAssert("button Login clicked", loginButton);
    }

    public void clickButtonMenu () {
        helper.elementClickNoAssert("button Login clicked", menuButton);
    }

    public void enterSomeDataInSearchInput () {
        helper.checkInputsActivity("input search check", searchInput);
        String enteringData = helper.randomNumRandomCount(20,10);
        helper.enterDataInInput("random value entering", searchInput, enteringData);
        helper.checkValueForElement("verify entered value visibility",enteringData, searchInput);
    }

    public void headerAssertion () {
        helper.getTextAssertion("button search assertion", searchButton, "Szukaj");
    }

    public void verifyInputSearch () {
        helper.isVisible(searchInput);
    }

    public void verifyMenu () {
        helper.isVisible(expandedMenu);
        for (int i = 1; i <= menuElements.size() ; i++) {
            String xPath = "(//div[@class=\"footer__box--content\"]/div/a)["+i+"]";
            helper.checkActivityElement(i+" element check", driver.findElement(By.xpath(xPath)), menuElements.get(i-1));
        }


    }

    public void goToLoginUsingMenu () {
        helper.handlingDropDownsWithAssert(menuButton, loginMenu, "Logowanie");
    }
    public void clearStorage() {
        helper.clearLocalStorage();
    }
}
