package testUtilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverFactory {

	public WebDriver getWebDriver(String browser) {

		switch (browser.toLowerCase()) {
		case "ie": {
			WebDriverManager.iedriver().setup();
			return setupWebDriver(new InternetExplorerDriver());
		}
		case "safari": {
			// Ensure to select "Allow Remote Automation" in your safari's developer
			// settings
			WebDriverManager.safaridriver().setup();
			return setupWebDriver(new SafariDriver());
		}
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			return setupWebDriver(new ChromeDriver(options));
		}
		default: {
			return null;
		}

		}
	}

	public WebDriver setupWebDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
