package com.patronite.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.fail;

public class SeleniumHelper {

    private Logger log = Logger.getLogger(SeleniumHelper.class);

    private WebDriver driver;

    public SeleniumHelper (WebDriver driver) {
        this.driver = driver;
    }


    public void waitForElementToBeDisplayed(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForListOfWebElements (List<WebElement> elementList) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
        wait.until(driver1 ->
                elementList.size()>0);
    }

    public static void takeScreenshot (WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/test/resources/screenshots/" + LocalTime.now().getNano() + ".png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());
    }

    public void isActive(WebElement element){
        waitForElementToBeDisplayed(element);
        if (element.isEnabled() && element.isDisplayed()) {log.info("ok");}
        else {log.error("element, should be active" +element);}
    }
    public void isVisible (WebElement element) {
        waitForElementToBeDisplayed(element);
        if (element.isDisplayed()) {log.info("ok");}
        else {log.error("element, should be visible" +element);}
    }

    public void isEnable (WebElement element) {
        waitForElementToBeDisplayed(element);
        if (element.isEnabled()) {log.info("ok");}
        else {log.error("element, should be enable" +element);}
    }

    public void changeTab () {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }
    public void clearLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("localStorage.clear();");
    }

    public String randomAlphabetic (int count) {
        String randomData;
        randomData = RandomStringUtils.randomAlphabetic(count);
        return randomData;
    }

    public String randomNum (int count) {
        String randomData;
        randomData = RandomStringUtils.randomAlphanumeric(count);
        return randomData;
    }

    public String randomAlphaNum (int count) {
        String randomData;
        randomData = RandomStringUtils.randomAlphanumeric(count);
        return randomData;
    }

    public void actionSendkeys (String sendData) {
        Actions actions = new Actions(driver);
        actions.sendKeys(sendData).build().perform();
    }

    public void actionClick (WebElement element, String enterLog){
        log.info(enterLog);
        waitForElementToBeDisplayed(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click(element).build().perform();
    }

    public void javaScriptClick (WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments{0}.click();", element);
    }

    public String randomNumRandomCount (int to, int from) {
        Random random = new Random();
        int randomCount = random.nextInt(to) +from;
        String randomData;
        randomData = RandomStringUtils.randomAlphabetic(randomCount);
        return randomData;
    }

    public String randomAlphaNumRandomCount (int to, int from) {
        Random random = new Random();
        int randomCount = random.nextInt(to) +from;
        String randomData;
        randomData = RandomStringUtils.randomAlphanumeric(randomCount);
        return randomData;
    }

    public void checkSizeImg(WebElement element, int height, int width) {
        int realHeight = element.getSize().getHeight();
        if (realHeight == height) {
            log.info("ok");
        }
            else {log.error("height not ok" + element + realHeight);
            fail("test");
        }
        int realWidth = element.getSize().getWidth();
        if (realWidth == width) {
            log.info("ok");
        }
        else {log.error("width not ok" + element + realWidth);
            fail("test");
        }
    }

    public void checkActivityElement (String enterLog, WebElement element, String expectedText) {
        log.info(enterLog);
        isActive(element);
        Assert.assertEquals(element.getText(), expectedText);
    }

    public void checkActivityElementValue (String enterLog, WebElement element, String expectedText) {
        log.info(enterLog);
        isActive(element);
        String text = element.getAttribute("value");
        Assert.assertEquals(text,expectedText);
    }

    public void checkDisplayingElement (String enterLog, WebElement element, String expectedText){
        log.info(enterLog);
        waitForElementToBeDisplayed(element);
        isVisible(element);
        Assert.assertEquals(element.getText(), expectedText);
    }

    public void checkInputsActivity (String enterLog, WebElement element) {
        log.info(enterLog);
        waitForElementToBeDisplayed(element);
        isActive(element);
    }

    public void getTextAssertion (String enterLog, WebElement element, String expectedText) {
        log.info(enterLog);
        waitForElementToBeDisplayed(element);
        Assert.assertEquals(element.getText(), expectedText);
    }

    public void elementClickWithAssert(String enterLog, WebElement element, String expectedText) {
        waitForElementToBeDisplayed(element);
        log.info(enterLog);
        Assert.assertEquals(element.getText(), expectedText);
        element.click();
    }

    public void elementClickWithAssertValue(String enterLog, WebElement element, String expectedText) {
        waitForElementToBeDisplayed(element);
        log.info(enterLog);
        String text = element.getAttribute("value");
        Assert.assertEquals(text,expectedText);
        element.click();
    }

    public void elementClickNoAssert(String enterLog, WebElement element) {
        waitForElementToBeDisplayed(element);
        log.info(enterLog);
        element.click();
    }

    public void handlingDropDowns (WebElement dropdownPath, WebElement optionDDPath){
        waitForElementToBeDisplayed(dropdownPath);
        dropdownPath.click();
        waitForElementToBeDisplayed(optionDDPath);
        optionDDPath.click();
    }
    public void handlingDropDownsWithAssert (WebElement dropdownPath, WebElement optionDDPath, String expectedText){
        waitForElementToBeDisplayed(dropdownPath);
        dropdownPath.click();
        waitForElementToBeDisplayed(optionDDPath);
        Assert.assertEquals(optionDDPath.getText(), expectedText);
        optionDDPath.click();
    }
    public void handlingAlerts (){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).build().perform();
    }
    public void checkValueForElement(String enterLog, String expectedValue, WebElement element){
        log.info(enterLog);
        String text = element.getAttribute("value");
        Assert.assertEquals(text,expectedValue);
    }
    public void enterDataInInput (String enterLog, WebElement element, String enteringData){
        log.info(enterLog);
        waitForElementToBeDisplayed(element);
        element.click();
        element.sendKeys(enteringData);
    }



}
