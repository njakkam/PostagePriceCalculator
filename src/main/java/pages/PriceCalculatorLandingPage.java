package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import lombok.Getter;
import pageUtilities.BasePage;
import pageUtilities.PageContext;

public class PriceCalculatorLandingPage extends BasePage {

	// Page class constants
	public final static String DEFAULT_DESTCOUNTRY_TEXT = "United States (Domestic and APO/FPO/DPO Mail)";
	public final static String DEFAULT_SHIPPINGTIME_TEXT = "between 8:00 a.m. - 8:30 a.m.";
	public final static By selectedListOption = By.xpath(".//option[@selected='selected']");
	// By locator for the page to wait to be displayed
	final By by_shippingDate = By.cssSelector("#ShippingDate");

	@FindBy(css = ".header-usps")
	@Getter
	WebElement pageHeader;

	@FindBy(css = "#CountryID")
	@Getter
	WebElement destCountry;

	@FindBy(css = "#Origin")
	@Getter
	WebElement origin;

	@FindBy(css = "#Destination")
	@Getter
	WebElement destination;

	@FindBy(css = "#ShippingDate")
	@Getter
	WebElement shippingDate;

	@FindBy(css = "#ShippingTime")
	@Getter
	WebElement shippingTime;

	@FindBy(css = ".field-validation-error")
	@Getter
	List<WebElement> zipCodeError;

	@FindBy(xpath = "//input[@value='ShapeAndSize']")
	@Getter
	WebElement shapeAndSizeButton;

	public PriceCalculatorLandingPage(PageContext context) {
		super(context);
		PageFactory.initElements(context.getDriver(), this);
	}

	@Override
	public ArrayList<WebElement> getDefaultElements() {
		ArrayList<WebElement> defaultElements = new ArrayList<>();
		defaultElements.add(destCountry);
		defaultElements.add(shippingDate);
		defaultElements.add(shippingTime);

		return defaultElements;
	}

	@Override
	public WebElement getPageHeaderElement() {
		return pageHeader;
	}

	@Override
	public WebElement getSectionHeaderElement() {
		return null;
	}

	@Override
	public void waitForPageElement() {
		this.context.getWait().until(ExpectedConditions.presenceOfElementLocated(by_shippingDate));
	}

	public void fillShippingDetails(String origin, String dest) {
		this.getOrigin().sendKeys(origin);
		this.getDestination().sendKeys(dest);
	}

	public void clearShippingDetails() {
		this.getOrigin().clear();
		this.getDestination().clear();
	}

	public void selectShippingDate(String date) {
		this.shippingDate.clear();
		this.shippingDate.sendKeys(date);
	}

	public WeightPage goToWeightPage() {
		shapeAndSizeButton.click();
		return new WeightPage(context);
	}

}
