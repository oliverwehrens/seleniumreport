package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import com.maxheapsize.seleniumreport.report.SeleniumReportEntry;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FirefoxLoggingListener extends AbstractWebDriverEventListener {

    private SeleniumReport seleniumReport;
    private static Logger log = LoggerFactory.getLogger(FirefoxLoggingListener.class);

    public FirefoxLoggingListener() {
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver webDriver) {
        SeleniumReportEntry entry = new SeleniumReportEntry("Clicked on '" + element.getTagName() + "' element with id '" + element.getAttribute("id") + "'");
        addEntryToExistingReport(entry, webDriver);
    }


    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver webDriver) {
        SeleniumReportEntry entry = new SeleniumReportEntry("Finding Element by " + by.toString());
        addEntryToExistingReport(entry, webDriver);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver webDriver) {
        SeleniumReportEntry entry = new SeleniumReportEntry("Changed value of element '" + element.getAttribute("id") + "' to '" + element.getAttribute("value") + "'");
        addEntryToExistingReport(entry, webDriver);
    }

    private void addEntryToExistingReport(SeleniumReportEntry entry, WebDriver webDriver) {
        if (seleniumReport != null) {
            String alwaysScreenshots = System.getProperty("selenium.report.screenshot.always");
            if (alwaysScreenshots != null && "true".equals(alwaysScreenshots)) {
                if (!entry.hasImage()) {
                    entry.setImage(makeScreenshot(webDriver));
                }
            }
            seleniumReport.addEntry(entry);
        } else {
            log.info("No selenium report available. Maybe missing " + SeleniumReportTestNGListener.class.getCanonicalName() + " Listener not enabled ?");
        }

    }


    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        File browserScreenShot = makeScreenshot(webDriver);
        SeleniumReportEntry entry = new SeleniumReportEntry("Error @ " + webDriver.getCurrentUrl());
        entry.setImage(browserScreenShot);
        entry.failure();
        addEntryToExistingReport(entry, webDriver);
    }

    private File makeScreenshot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    public void setSeleniumReport(SeleniumReport seleniumReport) {
        this.seleniumReport = seleniumReport;
    }

    public SeleniumReport getSeleniumReport() {
        return seleniumReport;
    }
}
