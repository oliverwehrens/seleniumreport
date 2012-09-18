package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import com.maxheapsize.seleniumreport.report.SeleniumTestNgHtmlConverter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SeleniumReportTestNGListenerTest {

    @InjectMocks
    private SeleniumReportTestNGListener listener;
    @Mock
    private SeleniumReport seleniumReportMock;

    @BeforeMethod
    public void setUp() {
        listener = new SeleniumReportTestNGListener();
        initMocks(this);
    }

    @Test
    public void onTestSuccessReportIsSuccessful() {
        ITestResult testResultMock = mock(ITestResult.class);
        when(testResultMock.getInstance()).thenReturn(mock(SeleniumReportable.class));

        listener.onTestSuccess(testResultMock);

        Mockito.verify(seleniumReportMock).successful();
    }

    @Test
    public void onTestFailureReportIsFailure() {
        ITestResult testResultMock = mock(ITestResult.class);
        when(testResultMock.getInstance()).thenReturn(mock(SeleniumReportable.class));

        listener.onTestFailure(testResultMock);

        Mockito.verify(seleniumReportMock).failure();
    }

    @Test
    public void newSeleniumReportsIsCreated() {
        assertThat(listener.getSeleniumReports()).isNotNull();
    }

    @Test
    public void onTestStart_SeleniumReportIsCreated() {
        ITestResult testResultMock = mock(ITestResult.class);
        SeleniumReportable seleniumTest = mock(SeleniumReportable.class);
        when(testResultMock.getInstance()).thenReturn(seleniumTest);
        ReportingWebDriver reportingWebDriverMock = mock(ReportingWebDriver.class);
        when(seleniumTest.getReportingWebDriver()).thenReturn(reportingWebDriverMock);
        when(reportingWebDriverMock.getFirefoxLoggingListener()).thenReturn(mock(FirefoxLoggingListener.class));
        when(testResultMock.getName()).thenReturn("DummyMethod");

        listener.onTestStart(testResultMock);

        assertThat(listener.getSeleniumReports().getReports()).hasSize(1);
    }

    @Test
    public void onTestStartWithNonSeleniumReportableNothingIsCreated() {
        ITestResult testResultMock = mock(ITestResult.class);

        listener.onTestStart(testResultMock);

        assertThat(listener.getSeleniumReports().getReports()).isEmpty();
    }


}
