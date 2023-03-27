package pageUtilities;

import java.util.List;

import org.openqa.selenium.WebElement;

public abstract class BasePage {

	protected PageContext context;

	public BasePage(PageContext context) {
		this.context = context;
	}

	public abstract WebElement getPageHeaderElement();

	public abstract WebElement getSectionHeaderElement();

	// List of page elements that are auto-filled with default values
	public abstract List<WebElement> getDefaultElements();

	public abstract void waitForPageElement();

}
