package com.checkIn.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/checkIn/stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html-report",
                "json:target/cucumber-reports/Cucumber.json"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
}

