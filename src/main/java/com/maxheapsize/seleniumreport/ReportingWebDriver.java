package com.maxheapsize.seleniumreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class ReportingWebDriver extends EventFiringWebDriver {

  private WebDriver webDriver;
  private FirefoxLoggingListener firefoxLoggingListener;

  public ReportingWebDriver(final WebDriver webDriver) {
    super(webDriver);
    this.firefoxLoggingListener = new FirefoxLoggingListener();
    this.webDriver = webDriver;
    if (webDriver instanceof FirefoxDriver) {
      register(firefoxLoggingListener);
    }
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public FirefoxLoggingListener getFirefoxLoggingListener() {
    return firefoxLoggingListener;
  }



}
