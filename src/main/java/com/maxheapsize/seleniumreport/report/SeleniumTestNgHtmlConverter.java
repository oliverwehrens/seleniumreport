package com.maxheapsize.seleniumreport.report;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class SeleniumTestNgHtmlConverter {

    private FileWriter fileWriter = new FileWriter();
    private SeleniumReportHtmlConverter seleniumReportHtmlConverter = new SeleniumReportHtmlConverter();
    private static Logger log = LoggerFactory.getLogger(SeleniumTestNgHtmlConverter.class);


    public void writeReportToDirectory(SeleniumReports seleniumReports, String reportDirectory) throws IOException {
        writeReportsIndex(seleniumReports.getReports(), reportDirectory);
        writeReport(seleniumReports.getReports(), reportDirectory);
    }

    void writeReportsIndex(Collection<SeleniumReport> seleniumReports, String reportDirectory) {
        String index = seleniumReportHtmlConverter.convertAllReports(seleniumReports);
        fileWriter.writeToFile(reportDirectory, index);
    }

    void writeReport(Collection<SeleniumReport> seleniumReports, String reportDirectory) {
        for (SeleniumReport seleniumReport : seleniumReports) {
            String testCase = seleniumReportHtmlConverter.convertSingleReport(seleniumReport);
            fileWriter.writeToFile(getSeleniumReportDirectory(reportDirectory, seleniumReport), testCase);
            copyScreenshotsToReportDir(getSeleniumReportDirectory(reportDirectory, seleniumReport), seleniumReport);
        }
    }

    String getSeleniumReportDirectory(String reportDirectory, SeleniumReport seleniumReport) {
        return reportDirectory + "/report-" + seleniumReport.getTestClass() + "-" + seleniumReport.getTestMethod();
    }

    private void copyScreenshotsToReportDir(String seleniumReportDir, SeleniumReport seleniumReport) {
        for (SeleniumReportEntry seleniumReportEntry : seleniumReport.getEntries()) {
            if (seleniumReportEntry.hasImage()) {
                File screenshot = seleniumReportEntry.getImage();
                try {
                    FileUtils.copyFile(screenshot, new File(seleniumReportDir + "/" + screenshot.getName()));
                } catch (IOException e) {
                    log.error("Could not copy screen shot " + screenshot.getAbsolutePath() + " to report directory.");
                }
            }
        }
    }
}
