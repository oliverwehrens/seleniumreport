package com.maxheapsize.seleniumreport.report;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class SeleniumReportHtmlConverter {

    private static Logger log = LoggerFactory.getLogger(SeleniumReportHtmlConverter.class);


    String convert(Object report, String template) {
        Map data = new HashMap();
        data.put("report", report);
        Configuration cfg = new Configuration();
        try {
            ClassTemplateLoader ctl = new ClassTemplateLoader(this.getClass(), "/com/maxheapsize/seleniumreport");
            cfg.setTemplateLoader(ctl);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template temp = cfg.getTemplate(template);
            Writer out = new StringWriter();
            temp.process(data, out);
            out.flush();
            return out.toString();
        } catch (IOException e) {
            log.error("Could not load template " + this.getClass() + ".");
        } catch (TemplateException e) {
            log.error("Could not use template " + this.getClass() + ".");
        }
        return "None";
    }

    String convertSingleReport(SeleniumReport report) {
        return convert(report, "SeleniumReportHtml.ftl");
    }

    String convertAllReports(Collection<SeleniumReport> reports) {
        return convert(reports, "AllSeleniumReportHtml.ftl");
    }
}
