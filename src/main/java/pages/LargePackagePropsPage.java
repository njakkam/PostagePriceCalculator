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

public class LargePackagePropsPage extends BasePage {

	// Page class constants
	public static final String PKGPROPS_SECTION_HEADER = "Large Package Properties";
	public final static String DEFAULT_RECTANGULAR_TEXT = "Rectangular";
	public final static String DEFAULT_STANDARDUNIT_TEXT = "Standard";
	// By locator for the page to wait to be displayed
	final By by_length = By.cssSelector("#Length");

	@FindBy(css = ".header-usps")
	@Getter
	WebElement pageHeader;

	@FindBy(xpath = "//input[@value='Continue']")
	@Getter
	WebElement continueButton;

	@FindBy(css = "#Rectangular")
	@Getter
	WebElement rectangular;

	@FindBy(css = "#Standard")
	@Getter
	WebElement standardUnit;

	@FindBy(tagName = "h2")
	@Getter
	WebElement sectionHeader;

	@FindBy(css = "#Length")
	@Getter
	WebElement lengthField;

	@FindBy(css = "#Height")
	@Getter
	WebElement heightField;

	@FindBy(css = "#Width")
	@Getter
	WebElement widthField;

	public LargePackagePropsPage(PageContext context) {
		super(context);
		PageFactory.initElements(context.getDriver(), this);
	}

	@Override
	public ArrayList<WebElement> getDefaultElements() {
		ArrayList<WebElement> defaultElements = new ArrayList<>();
		defaultElements.add(rectangular);
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
		this.context.getWait().until(ExpectedConditions.presenceOfElementLocated(by_length));
	}

	public void fillPackageDimensions(String length, String height, String width) {
		lengthField.sendKeys(length.toString());
		heightField.sendKeys(height.toString());
		widthField.sendKeys(width.toString());
	}

	public MailServicesPage goToMailServicesPage() {
		continueButton.click();
		return new MailServicesPage(this.context);
	}

}
