package assignmentSelenium.testCases;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
			features="src/test/resources/Features",
			glue="assignmentSelenium.stepDefns",
			tags="@Assignment",
			monochrome=true,
			dryRun=false,
			publish=true,
			plugin = { "pretty", "json:target/Cucumber.json",
					"junit:target/Cucumber.xml",
					"html:target/cucumber-reports"}

		)
public class TestRunnerBDD extends AbstractTestNGCucumberTests {

}
