package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = { "src/test/java/features/postagepricecalculation.feature" },
		// dryRun = true,
		snippets = SnippetType.CAMELCASE, monochrome = true, glue = { "tests", "hooks", "pages" }, plugin = { "pretty",
				"html:target/cucumber_reports/ChromeCumcumberReports.html" })

public class ChromeRunner extends Runner {

}
