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

        if (properties == null) {
            try {
                properties.load(new FileInputStream("config.properties"));
                testFramework = properties.getProperty("FRAMEWORK").trim().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (testFramework.equalsIgnoreCase("CUCUMBER")) {
            String scenarioName = scenario.getName().toUpperCase().trim();

            if (scenarioName.contains("TESTRAILID:")) {
                testRailId = Integer.valueOf(
                        scenarioName.split("TESTRAIL:")[0].split("]")[0].trim()
                );
            } else {
                testRailId = 0;
            }
        }

        return testRailId;
    }
}
