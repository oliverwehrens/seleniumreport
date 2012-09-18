package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SeleniumReportTestNGListenerOnStartTest {

    @InjectMocks
    private SeleniumReportTestNGListener listener;
    @Mock
    private SeleniumReport seleniumReportMock;
    @Mock
    private SeleniumReportable seleniumReportableMock;
    @Mock
    private ReportingWebDriver reportingWebDriverMock;
    @Mock
    private ITestResult testResultMock;
    @Mock
    private FirefoxLoggingListener firefoxLoggingListenerMock;

    @BeforeMethod
    public void setUp() {
        listener = new SeleniumReportTestNGListener();
        initMocks(this);

        when(testResultMock.getInstance()).thenReturn(seleniumReportableMock);
        when(seleniumReportableMock.getReportingWebDriver()).thenReturn(reportingWebDriverMock);
        when(reportingWebDriverMock.getFirefoxLoggingListener()).thenReturn(firefoxLoggingListenerMock);
        when(testResultMock.getName()).thenReturn("DummyMethod");
    }

    @Test
    public void seleniumReportIsCreated() {
        listener.onTestStart(testResultMock);

        assertThat(listener.getSeleniumReports().getReports()).hasSize(1);
    }

    @Test
    public void reportIsSetToFirefoxLoggingListener() {
        listener.onTestStart(testResultMock);

        Mockito.verify(firefoxLoggingListenerMock).setSeleniumReport((SeleniumReport) Mockito.any());
    }

    @Test
    public void testMethodIsSet() {
        listener.onTestStart(testResultMock);

        assertThat(listener.getSeleniumReports().getReports().iterator().next().getTestMethod()).isEqualTo("DummyMethod");
    }

    @Test
    public void testClassIsSet() {
        listener.onTestStart(testResultMock);

        assertThat(listener.getSeleniumReports().getReports().iterator().next().getTestClass()).startsWith("com.maxheapsize.seleniumreport.SeleniumReportable$$EnhancerByMockitoWithCGLIB$$");
    }

}
