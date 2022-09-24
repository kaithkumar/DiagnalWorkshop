package com.runners;
import static io.cucumber.junit.CucumberOptions.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter: ",
                "html:target/cucumber/report.html"}
        ,features = {"src/main/resources",}
        ,glue = {"com.stepDefinition"}
        ,snippets = SnippetType.CAMELCASE
        ,dryRun=false
        ,monochrome=true
        ,tags = "@test"

)

public class TestRunner {
    @BeforeClass
    public static void initialize() throws Exception {
        BaseClass base =new BaseClass();
        base.startServer();
    }

    @AfterClass
    public static void quit(){

    	BaseClass base =new BaseClass();
        if(base.getServer() != null){
           base.getServer().stop();
        }
    }
    

}
