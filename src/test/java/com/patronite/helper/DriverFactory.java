package com.patronite.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver driverInstance;

    public static WebDriver getDriver (DriverType driverType) throws NoSuchDriverException {

        if (driverInstance == null) {
            getSpecificDriver (driverType);

            driverInstance.manage().window().maximize();
            driverInstance.manage().deleteAllCookies();
        }
        return driverInstance;
    }

    private static void getSpecificDriver(DriverType driverType) throws NoSuchDriverException {

        switch (driverType) {
            case CHROME:
                File chromeExe = new File("src//test//resources//drivers//executables//chromedriver.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeExe)
                        .usingAnyFreePort().build();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                driverInstance = new ChromeDriver(chromeService, options);
                break;
            case FIREFOX:
                File fireFoxExe = new File("src//main//resources//executables//drivers//geckodriver.exe");
                GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(fireFoxExe)
                        .usingAnyFreePort().build();

                driverInstance = new FirefoxDriver(geckoDriverService);
                break;
            default:
                System.out.println("Brak drivera");
                throw new NoSuchDriverException ();
        }
    }

}
