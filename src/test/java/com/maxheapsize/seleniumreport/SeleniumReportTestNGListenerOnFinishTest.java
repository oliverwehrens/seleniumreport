package com.maxheapsize.seleniumreport;

import com.maxheapsize.seleniumreport.report.SeleniumReport;
import com.maxheapsize.seleniumreport.report.SeleniumTestNgHtmlConverter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SeleniumReportTestNGListenerOnFinishTest {

  @InjectMocks
  private SeleniumReportTestNGListener listener;
  @Mock
  private SeleniumReport seleniumReportMock;
  @Mock
  private SeleniumTestNgHtmlConverter seleniumTestNgHtmlConverterMock;
  @Mock
  private ITestContext iTestContextMock;

  @BeforeMethod
  public void setUp() {
    listener = new SeleniumReportTestNGListener();
    initMocks(this);
  }

  @Test
  public void onFinish_WriteReport() throws IOException {
    listener.onFinish(iTestContextMock);

    Mockito.verify(seleniumTestNgHtmlConverterMock).writeReportToDirectory(listener.getSeleniumReports(), ".");
  }

  @Test
  public void onFinish_throwException() throws IOException {
    doThrow(new IOException()).when(seleniumTestNgHtmlConverterMock).writeReportToDirectory(listener.getSeleniumReports(), ".");
    listener.onFinish(iTestContextMock);

  }

}
