package pageUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;
import lombok.Setter;

public class PageContext {

	@Getter
	@Setter
	private WebDriver driver;
	@Getter
	@Setter
	private WebDriverWait wait;

}
