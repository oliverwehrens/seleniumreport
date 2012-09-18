package com.maxheapsize.seleniumreport.report;

import org.testng.annotations.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class SeleniumReportEntryTest {

    @Test
    public void imageFileNameReturnsFileName() {
        SeleniumReportEntry seleniumReportEntry = new SeleniumReportEntry("Demo");
        seleniumReportEntry.setImage(new File("/tmp/test"));

        assertThat(seleniumReportEntry.getImageFileName()).isEqualTo("test");
    }

    @Test
    public void noImageFileReturnsEmptyString() {
        SeleniumReportEntry seleniumReportEntry = new SeleniumReportEntry("Demo");

        assertThat(seleniumReportEntry.getImageFileName()).isEmpty();
    }

    @Test
    public void hasImageWithNoImageInEntryHasNoImage() {
        SeleniumReportEntry seleniumReportEntry = new SeleniumReportEntry("Demo");

        assertThat(seleniumReportEntry.hasImage()).isFalse();
    }

    @Test
    public void hasImageWithImageInEntryHasImage() {
        SeleniumReportEntry seleniumReportEntry = new SeleniumReportEntry("Demo");
        seleniumReportEntry.setImage(new File("/tmp/test"));

        assertThat(seleniumReportEntry.hasImage()).isTrue();
    }

}
