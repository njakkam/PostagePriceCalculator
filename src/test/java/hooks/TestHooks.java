package hooks;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageUtilities.PageContext;
import testRunner.Runner;
import testUtilities.ReportHelper;
import testUtilities.WebdriverFactory;

public class TestHooks {

	public static PageContext context;
	private WebdriverFactory driverFactory;

	public TestHooks() {
		context = new PageContext();
		this.driverFactory = new WebdriverFactory();
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		String browser = Runner.BROWSER.get();
		context.setDriver(driverFactory.getWebDriver(browser));
		context.setWait(new WebDriverWait(context.getDriver(), Duration.ofSeconds(30)));
	}

	@After(order = 1)
	public void afterScenarioScreenshot(Scenario scenario) throws IOException {
		boolean failed = scenario.isFailed();
		if (failed) {
			TakesScreenshot ts = (TakesScreenshot) context.getDriver();
			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", ReportHelper.getScreenshotName());
		}
	}

	@After(order = 0)
	public void afterScenarioTeardown(Scenario scenario) throws IOException {
		context.getDriver().quit();

	}

}
