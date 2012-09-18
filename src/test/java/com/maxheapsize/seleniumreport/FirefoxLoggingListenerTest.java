package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FirefoxLoggingListenerTest {

    private FirefoxLoggingListener firefoxLoggingListener;

    @Mock
    private WebElement webElementMock;

    @Mock
    private WebDriver webDriverMock;

    @Mock
    private Throwable throwableMock;

    @BeforeMethod
    public void setUp() {
        firefoxLoggingListener = new FirefoxLoggingListener();
        firefoxLoggingListener.setSeleniumReport(new SeleniumReport("ff"));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void beforeClickOnCreatedEntry() {
        firefoxLoggingListener.beforeClickOn(webElementMock, webDriverMock);
        assertThat(firefoxLoggingListener.getSeleniumReport().getEntries().get(0).getText()).contains("Clicked on");
    }

    @Test
    public void afterChangeValueOfCreatedEntry() {
        firefoxLoggingListener.afterChangeValueOf(webElementMock, webDriverMock);
        assertThat(firefoxLoggingListener.getSeleniumReport().getEntries().get(0).getText()).contains("Changed value of element");
    }

    @Test
    public void noExceptionIfNoSeleniumReportAvailable() {
        firefoxLoggingListener.setSeleniumReport(null);

        firefoxLoggingListener.beforeClickOn(webElementMock, webDriverMock);

        assertThat(firefoxLoggingListener.getSeleniumReport()).isNull();
    }

}
