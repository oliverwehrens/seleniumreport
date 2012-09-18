package com.maxheapsize.seleniumreport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ExampleIntegrationTest implements SeleniumReportable {

    private ReportingWebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ReportingWebDriver(new FirefoxDriver());
    }

    @Test(enabled = false)
    public void testDriverOnGoogle() {
        driver.get("http://www.google.com");
        WebElement logo = driver.findElement(By.id("hplogo"));
        logo.click();

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();

        WebElement googleLink = driver.findElement(By.id("gbqlw"));
        googleLink.click();

        String title = driver.getTitle();
        driver.close();
        assertThat(title).isEqualTo("Cheese! - Google-Suche");
    }

    @Override
    public ReportingWebDriver getReportingWebDriver() {
        return driver;
    }
}
