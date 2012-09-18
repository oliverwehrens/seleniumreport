package com.maxheapsize.seleniumreport.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SeleniumReports {

  private List<SeleniumReport> reports = new ArrayList<SeleniumReport>();

  public Collection<SeleniumReport> getReports() {
    return Collections.unmodifiableCollection(reports);
  }

  public void add(SeleniumReport seleniumReport) {
    reports.add(seleniumReport);
  }
}
