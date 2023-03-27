package testRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class Runner extends AbstractTestNGCucumberTests {

	public final static ThreadLocal<String> BROWSER = new ThreadLocal<>();

	@BeforeTest
	@Parameters({ "Browser" })
	public void defineBrowser(String browser) {
		BROWSER.set(browser);
		System.out.println(browser);
	}

}
