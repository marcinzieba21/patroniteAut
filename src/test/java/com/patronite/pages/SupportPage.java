package com.patronite.pages;

import com.patronite.helper.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportPage {

    @FindBy (css = "#patron > section.hiw__main__section.icon.patron > h2")
    private WebElement header;


    private SeleniumHelper helper;



    public SupportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);


}

    public void headerCheck() {
        helper.getTextAssertion("header check", header, "Patron\n" +
                "to osoba, która wspiera rozwój talentów\n" +
                "nie tylko dobrym słowem, ale i finansowo!");
    }



}