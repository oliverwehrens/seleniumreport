package com.maxheapsize.seleniumreport.report;


import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SeleniumReportTest {

    @Test
    public void testClassNameIsTruncated() {
        SeleniumReport seleniumReport = new SeleniumReport("demo.my");
        assertThat(seleniumReport.getTestClassShortName()).isEqualTo("my");
    }

    @Test
    public void testClassNameIsTruncatedWithDots() {
        SeleniumReport seleniumReport = new SeleniumReport("my");
        assertThat(seleniumReport.getTestClassShortName()).isEqualTo("my");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nullIsNotAllowed() {
        SeleniumReport seleniumReport = new SeleniumReport(null);
        seleniumReport.getTestClassShortName();
    }

    @Test
    public void markedAsFailure() {
        SeleniumReport seleniumReport = new SeleniumReport("demo");

        seleniumReport.failure();

        assertThat(seleniumReport.isSuccess()).isFalse();
    }

    @Test
    public void markedAsSuccess() {
        SeleniumReport seleniumReport = new SeleniumReport("demo");

        seleniumReport.successful();

        assertThat(seleniumReport.isSuccess()).isTrue();
    }


}
