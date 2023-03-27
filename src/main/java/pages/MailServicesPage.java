package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import lombok.Getter;
import pageUtilities.BasePage;
import pageUtilities.PageContext;

public class MailServicesPage extends BasePage {

	// Page class constants
	public static final String MAILSERVICES_SECTION_HEADER = "Mail Services";
	// By locator for the page to wait to be displayed
	final By by_length = By.cssSelector(".hidden-sm img");

	@FindBy(css = ".header-usps")
	@Getter
	WebElement pageHeader;

	@FindBy(tagName = "h2")
	@Getter
	WebElement sectionHeader;

	@FindBy(css = ".hidden-sm img")
	@Getter
	List<WebElement> imgList;

	@FindBy(css = "#mail-services-sm-lg")
	@Getter
	List<WebElement> mailOptions;

	@FindBy(xpath = "//h4[text()='USPS Retail Ground']//parent::div//td[starts-with(.,'$')]")
	@Getter
	WebElement pkgPrice;

	public MailServicesPage(PageContext context) {
		super(context);
		PageFactory.initElements(context.getDriver(), this);
	}

	@Override
	public ArrayList<WebElement> getDefaultElements() {
		ArrayList<WebElement> defaultElements = new ArrayList<>();
		defaultElements.addAll(imgList);
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
		this.context.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".hidden-sm img")));
	}

	// Extracts the $ amount for the Package Price
	public double getRetailPrice() throws IllegalStateException, IllegalArgumentException {
		String s = pkgPrice.getText();
		Pattern p = Pattern.compile("([0-9]*\\.[0-9]*)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		m.find(0);

		return Double.parseDouble(m.group(1));
	}

}
