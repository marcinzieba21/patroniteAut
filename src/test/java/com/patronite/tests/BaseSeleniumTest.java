package com.patronite.tests;

import com.patronite.helper.DriverFactory;
import com.patronite.helper.DriverType;
import com.patronite.helper.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        String driverPath = "D:\\Udemy\\intelliJ\\kursselenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        try {
            driver = DriverFactory.getDriver(DriverType.CHROME);
        } catch (NoSuchDriverException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown () {
        driver.quit();

    }
}
