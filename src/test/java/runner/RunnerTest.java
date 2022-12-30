package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import support.BaseSteps;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src"},
        snippets = SnippetType.CAMELCASE,
        plugin = {"json:target/reports/CucumberReport.json"},
//				  "pretty"},
        monochrome = false,
        dryRun = false,
        glue = {"steps","support"},
        tags = {"~@ignore", "@integrado"})

public class RunnerTest extends BaseSteps {

}



