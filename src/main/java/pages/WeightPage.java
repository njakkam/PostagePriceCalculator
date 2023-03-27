package pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import lombok.Getter;
import pageUtilities.BasePage;
import pageUtilities.PageContext;

public class WeightPage extends BasePage {
	// Page class constants
	public static final String WEIGHT_SECTION_HEADER = "Weight and Shape/Size";
	public final static String DEFAULT_STANDARDUNIT_TEXT = "Standard";
	// By locator for the page to wait to be displayed
	final By by_pounds = By.cssSelector("#Pounds");

	@FindBy(css = ".header-usps")
	@Getter
	WebElement pageHeader;

	@FindBy(css = "#Standard")
	@Getter
	WebElement standardUnit;

	@FindBy(css = "#Pounds")
	@Getter
	WebElement pounds;

	@FindBy(tagName = "h2")
	@Getter
	WebElement sectionHeader;

	@FindBy(xpath = "//input[@value='LargePackage']")
	@Getter
	WebElement largePackageButton;

	public WeightPage(PageContext context) {
		super(context);
		PageFactory.initElements(context.getDriver(), this);
	}

	@Override
	public ArrayList<WebElement> getDefaultElements() {
		ArrayList<WebElement> defaultElements = new ArrayList<>();
		defaultElements.add(standardUnit);
		return defaultElements;
	}

	@Override
	public WebElement getPageHeaderElement() {
		return pageHeader;
	}

	@Override
	public WebElement getSectionHeaderElement() {
		return sectionHeader;
	}

	@Override
	public void waitForPageElement() {
		this.context.getWait().until(ExpectedConditions.presenceOfElementLocated(by_pounds));
	}

	public void fillShippingWeight(String weight) {
		pounds.sendKeys(weight);
	}

	public LargePackagePropsPage goToLargePackagePropsPage() {
		largePackageButton.click();
		return new LargePackagePropsPage(this.context);
	}

}