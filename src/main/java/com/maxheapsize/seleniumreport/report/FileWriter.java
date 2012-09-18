package com.maxheapsize.seleniumreport.report;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileWriter {

    public void writeToFile(String baseDir, String content) {
        try {
            FileUtils.forceMkdir(new File(baseDir));
            File index = new File(baseDir+"/index.html");
            FileUtils.writeStringToFile(index, content);
        }
        catch (IOException e) {
//TODO
            e.printStackTrace();
        }
    }

}
