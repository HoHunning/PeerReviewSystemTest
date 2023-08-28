package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class TestCaseCreator {
    public static void createTestCases(int numOfTestCases) throws IOException, TemplateException{
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(TestCaseCreator.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates/PeerReviewSystem/"));
        Template template = cfg.getTemplate("PeerReviewSystemTest.java.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("numOfTestCases", numOfTestCases);

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            FileWriter fw = new FileWriter("src/test/java/com/example/demo/PeerReviewSystem/PeerReviewSystemTest.java");
            fw.write(out.toString());
            fw.close();
        }

    }
}
