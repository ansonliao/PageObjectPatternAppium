package com.appium.testrail;

import gherkin.formatter.model.Scenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by AnsonLiao on 2/9/2016.
 */
public class TestRailUtils {
    private Scenario scenario;
    private int testRailId;
    private static Properties properties;

    private static final String CONFIG_PROPERTY = "config.properties";
    private static final String TSET_FRAMEWORK_PROPERTY = "FRAMEWORK";
    private static final String TESTNG_FRAMEWORK = "TESTNG";
    private static final String CUCUMBER_FRAMEWORK = "CUCUMBER";

    public TestRailUtils(Scenario scenario) {
        this.scenario = scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void setTestRailId(int testRailId) {
        this.testRailId = testRailId;
    }

    public int getTestRailId() {
        String testFramework = "";
        String splitWord1 = "TESTRAILID:";
        String splitWord2 = "]";

        if (properties == null) {
            try {
                properties.load(new FileInputStream(CONFIG_PROPERTY));
                testFramework = properties.getProperty(TSET_FRAMEWORK_PROPERTY)
                        .trim().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (testFramework.equalsIgnoreCase(CUCUMBER_FRAMEWORK)) {
            String scenarioName = scenario.getName().toUpperCase().trim();

            if (scenarioName.contains(splitWord1)) {
                testRailId = Integer.valueOf(
                        scenarioName.split(splitWord1)[0].split(splitWord2)[0].trim());
            } else {
                testRailId = 0;
            }
        }
        
        if (testFramework.equalsIgnoreCase(TESTNG_FRAMEWORK)) {
            //TODO: 2/9/2016
        }

        return testRailId;
    }
}
