package com.maxheapsize.seleniumreport.report;

import java.util.ArrayList;
import java.util.List;

public class SeleniumReport {

    String testClass;
    String testMethod;
    private boolean success = false;
    private List<SeleniumReportEntry> entries = new ArrayList<SeleniumReportEntry>();

    public SeleniumReport(String testClass) {
        this.testClass = testClass;
    }

    public void successful() {
        success = true;
    }

    public void failure() {
        success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTestMethod() {
        return testMethod != null ? testMethod : "Unknown";
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public void addEntry(SeleniumReportEntry entry) {
        entries.add(entry);
    }

    public String getTestClass() {
        return testClass != null ? testClass : "Unknown";
    }

    public List<SeleniumReportEntry> getEntries() {
        return entries;
    }

    public String getTestClassShortName() {
        if (testClass == null) {
            throw new IllegalArgumentException("Testclass name can not be null.");
        }
        int position = testClass.lastIndexOf(".");
        return testClass.substring(position + 1, testClass.length());
    }
}
