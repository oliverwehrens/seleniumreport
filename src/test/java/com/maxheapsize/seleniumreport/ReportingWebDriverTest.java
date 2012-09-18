package com.maxheapsize.seleniumreport;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReportingWebDriverTest {

  private ReportingWebDriver driver;
  @Mock
  private FirefoxDriver firefoxDriverMock;

  @BeforeMethod
  public void setUp() {
    initMocks(this);
    driver = new ReportingWebDriver(firefoxDriverMock);
  }

  @Test
  public void firefoxListener_IsRegistered() {
    assertThat(driver.getFirefoxLoggingListener()).isNotNull();
  }

  @Test
  public void getWebdriver() {
    assertThat(driver.getWebDriver()).isEqualTo(firefoxDriverMock);
  }
}
