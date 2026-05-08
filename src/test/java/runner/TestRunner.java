package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features",
        glue={"stepDefinitions"},
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/CucumberReports/CucumberReport.html"
        },
        dryRun=false,
        monochrome=true,
        publish=false

)
public class TestRunner
{

}
