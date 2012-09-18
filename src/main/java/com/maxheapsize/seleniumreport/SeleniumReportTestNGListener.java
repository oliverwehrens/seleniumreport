package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import com.maxheapsize.seleniumreport.report.SeleniumReports;
import com.maxheapsize.seleniumreport.report.SeleniumTestNgHtmlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;

public class SeleniumReportTestNGListener extends TestListenerAdapter {

    private static Logger log = LoggerFactory.getLogger(SeleniumReportTestNGListener.class);

    private SeleniumReport currentSeleniumReport;
    private SeleniumReports seleniumReports;
    private SeleniumTestNgHtmlConverter seleniumTestNgHtmlConverter;

    public SeleniumReportTestNGListener() {
        seleniumReports = new SeleniumReports();
        seleniumTestNgHtmlConverter = new SeleniumTestNgHtmlConverter();
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        super.onTestStart(testResult);
        Object seleniumTest = testResult.getInstance();
        if (seleniumTest instanceof SeleniumReportable) {
            ReportingWebDriver reportingWebDriver = ((SeleniumReportable) seleniumTest).getReportingWebDriver();
            currentSeleniumReport = new SeleniumReport(seleniumTest.getClass().getCanonicalName());
            currentSeleniumReport.setTestMethod(testResult.getName());
            reportingWebDriver.getFirefoxLoggingListener().setSeleniumReport(currentSeleniumReport);
            seleniumReports.add(currentSeleniumReport);
        }
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        super.onTestSuccess(testResult);
        if (testResult.getInstance() instanceof SeleniumReportable) {
            currentSeleniumReport.successful();
        }
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        super.onTestFailure(testResult);
        if (testResult.getInstance() instanceof SeleniumReportable) {
            currentSeleniumReport.failure();
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        String seleniumReportDir = System.getProperty("selenium.report.dir");
        if (seleniumReportDir == null) {
            seleniumReportDir = ".";
        }
        try {
            seleniumTestNgHtmlConverter.writeReportToDirectory(seleniumReports, seleniumReportDir);
        } catch (IOException e) {
            log.error("Could not write selenium reports to " + seleniumReportDir + ".");
        }
    }

    public SeleniumReports getSeleniumReports() {
        return seleniumReports;
    }
}