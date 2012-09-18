package com.maxheapsize.seleniumreport.report;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import com.maxheapsize.seleniumreport.report.SeleniumReportEntry;
import com.maxheapsize.seleniumreport.report.SeleniumReportHtmlConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SeleniumReportHtmlConverterTest {

  private SeleniumReportHtmlConverter converter;

  @BeforeMethod
  public void setUp() {
    converter = new SeleniumReportHtmlConverter();
  }

  @Test
  public void testConvertHtmlTagIsRendered() {
    SeleniumReport dummy = new SeleniumReport("Dummy");
    dummy.setTestMethod("test");
    String html = converter.convertSingleReport(dummy);

    assertThat(html).startsWith("<html>");
  }

  @Test
  public void testConvertEntryIsRendered() {
    SeleniumReport dummy = new SeleniumReport("Dummy");
    dummy.setTestMethod("test");
    dummy.addEntry(new SeleniumReportEntry("Demo"));
    String html = converter.convertSingleReport(dummy);

    assertThat(html).contains("Demo");
  }
}
