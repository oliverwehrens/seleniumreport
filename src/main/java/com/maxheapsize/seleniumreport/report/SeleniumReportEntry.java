package com.maxheapsize.seleniumreport.report;

import java.io.File;
import java.util.Date;

public class SeleniumReportEntry {

    private Date date;
    private String text;
    private File image;
    private boolean success = true;

    public SeleniumReportEntry(String text) {
        this.date = new Date();
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public boolean hasImage() {
        return image != null;
    }

    public String getImageFileName() {
        if (image != null) {
            return image.getName();
        }
        return "";
    }

    public boolean isSuccess() {
        return success;
    }

    public void failure() {
        success = false;
    }
}

