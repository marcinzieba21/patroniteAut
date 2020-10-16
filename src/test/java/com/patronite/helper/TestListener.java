package com.patronite.helper;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    Logger log = Logger.getLogger(TestListener.class);


    @Override
    public void onTestStart(ITestResult iTestResult) {
    log.debug("OnTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    log.debug("OnTestSuccess");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            log.debug("OnTestFailure");
            SeleniumHelper.takeScreenshot(DriverFactory.getDriver(DriverType.CHROME));
        } catch (IOException e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
        } catch (NoSuchDriverException e) {
            log.error(e.getStackTrace());
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.debug("OnTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.debug("OnTestFailureButWithin");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.debug("OnStart");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.debug("OnTestFinish");
    }
}
