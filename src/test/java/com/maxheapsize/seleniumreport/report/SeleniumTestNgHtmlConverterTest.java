package com.maxheapsize.seleniumreport.report;

import com.google.common.io.Files;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SeleniumTestNgHtmlConverterTest {

    @InjectMocks
    private SeleniumTestNgHtmlConverter seleniumTestNgHtmlConverter;

    @Mock
    private FileWriter fileWriterMock;

    @Mock
    private SeleniumReportHtmlConverter seleniumReportHtmlConverterMock;

    private String reportDirectory;

    @BeforeMethod
    public void setUp() {
        seleniumTestNgHtmlConverter = new SeleniumTestNgHtmlConverter();
        initMocks(this);
        reportDirectory = Files.createTempDir().getAbsolutePath();
    }

    @Test
    public void allReportsIndexFileIsWritten() {
        ArrayList<SeleniumReport> allReports = new ArrayList<SeleniumReport>();
        when(seleniumReportHtmlConverterMock.convertAllReports(allReports)).thenReturn("OUT");

        seleniumTestNgHtmlConverter.writeReportsIndex(allReports, reportDirectory);

        verify(fileWriterMock).writeToFile(reportDirectory, "OUT");
    }

    @Test
    public void allReportsHtmlConverterIsUsed() {
        ArrayList<SeleniumReport> allReports = new ArrayList<SeleniumReport>();

        seleniumTestNgHtmlConverter.writeReportsIndex(allReports, reportDirectory);

        verify(seleniumReportHtmlConverterMock).convertAllReports(allReports);
    }

    @Test
    public void singleReportHtmlConverterIsCalled() {
        ArrayList<SeleniumReport> seleniumReports = new ArrayList<SeleniumReport>();
        SeleniumReport seleniumReport = new SeleniumReport("demo.class");
        seleniumReports.add(seleniumReport);

        seleniumTestNgHtmlConverter.writeReport(seleniumReports, reportDirectory);

        verify(seleniumReportHtmlConverterMock).convertSingleReport(seleniumReport);
    }

    @Test
    public void singleReportIndexFileIsWritten() {
        ArrayList<SeleniumReport> seleniumReports = new ArrayList<SeleniumReport>();
        SeleniumReport seleniumReport = new SeleniumReport("demo.class");
        seleniumReports.add(seleniumReport);
        when(seleniumReportHtmlConverterMock.convertSingleReport(seleniumReport)).thenReturn("OUT");
        String singleSeleniumReportDirectory = seleniumTestNgHtmlConverter.getSeleniumReportDirectory(reportDirectory, seleniumReport);

        seleniumTestNgHtmlConverter.writeReport(seleniumReports, reportDirectory);

        verify(fileWriterMock).writeToFile(singleSeleniumReportDirectory, "OUT");
    }




}
