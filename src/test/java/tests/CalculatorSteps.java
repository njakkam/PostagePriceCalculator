package tests;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import hooks.TestHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LargePackagePropsPage;
import pages.MailServicesPage;
import pages.PriceCalculatorLandingPage;
import pages.WeightPage;

public class CalculatorSteps {

	// Page objects
	private PriceCalculatorLandingPage priceCalcPage;
	private LargePackagePropsPage largePackagePropsPage;
	private MailServicesPage mailServicesPage;
	private WeightPage weightPage;

	// Test class constants
	final String POSTAGE_LANDINGPAGE_URL = "https://postcalc.usps.com/";
	final String ORIGIN_ZIPCODE = "78727";
	final String DEST_ZIPCODE = "94107";
	final String SHIPPING_DATE = "3/14/2023";

	@Given("I navigate to the Postage Price calculator page")
	public void inavigateToThePostagePriceCalculatorPage() {
		TestHooks.context.getDriver().get(POSTAGE_LANDINGPAGE_URL);
		this.priceCalcPage = new PriceCalculatorLandingPage(TestHooks.context);
	}

	@When("I fill in the shipping details on the shipping form")
	public void iFillInTheShippingDetails() {
		// Wait for page loading
		this.priceCalcPage.waitForPageElement();

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = priceCalcPage.getDefaultElements();
		Assert.assertEquals(
				defaultElements.get(0).findElement(PriceCalculatorLandingPage.selectedListOption).getText().trim(),
				PriceCalculatorLandingPage.DEFAULT_DESTCOUNTRY_TEXT);
		Assert.assertNotNull(defaultElements.get(1).getAttribute("value"));
		Assert.assertEquals(
				defaultElements.get(2).findElement(PriceCalculatorLandingPage.selectedListOption).getText().trim(),
				PriceCalculatorLandingPage.DEFAULT_SHIPPINGTIME_TEXT);

		// Verify presence of remaining elements
		Assert.assertNotNull(priceCalcPage.getOrigin());
		Assert.assertNotNull(priceCalcPage.getDestination());
		Assert.assertNotNull(priceCalcPage.getShapeAndSizeButton());

		// Fill up the page form details
		priceCalcPage.selectShippingDate(SHIPPING_DATE);
		priceCalcPage.fillShippingDetails(ORIGIN_ZIPCODE, DEST_ZIPCODE);

		// Navigate to the next page
		this.weightPage = priceCalcPage.goToWeightPage();
	}

	@When("I fill in the shipping weight of {int} on the weight form")
	public void iFillInTheShippingWeight(Integer weight) {
		// Wait for page loading
		this.weightPage.waitForPageElement();

		// Verify section header title
		Assert.assertEquals(weightPage.getSectionHeaderElement().getText(), WeightPage.WEIGHT_SECTION_HEADER);

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = weightPage.getDefaultElements();
		Assert.assertEquals(defaultElements.get(0).getAttribute("class"), "active");
		Assert.assertEquals(defaultElements.get(0).getText(), WeightPage.DEFAULT_STANDARDUNIT_TEXT);

		// Verify presence of remaining elements
		Assert.assertNotNull(weightPage.getStandardUnit());
		Assert.assertNotNull(weightPage.getLargePackageButton());

		// Fill up the page form details
		weightPage.fillShippingWeight(weight.toString());

		// Navigate to the next page
		this.largePackagePropsPage = weightPage.goToLargePackagePropsPage();
	}

	@When("I fill in the shipping length of {int}, height of {int} & width of {int} on the package properties form")
	public void iFillTheShippingLengthHeightWidth(Integer length, Integer width, Integer height) {
		// Wait for page loading
		this.largePackagePropsPage.waitForPageElement();

		// Verify section header title
		Assert.assertEquals(largePackagePropsPage.getSectionHeaderElement().getText(),
				LargePackagePropsPage.PKGPROPS_SECTION_HEADER);

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = largePackagePropsPage.getDefaultElements();
		Assert.assertEquals(defaultElements.get(0).getAttribute("class"), "active");
		Assert.assertEquals(defaultElements.get(0).getText(), LargePackagePropsPage.DEFAULT_RECTANGULAR_TEXT);
		Assert.assertEquals(defaultElements.get(1).getAttribute("class"), "active");
		Assert.assertEquals(defaultElements.get(1).getText(), LargePackagePropsPage.DEFAULT_STANDARDUNIT_TEXT);

		// Verify presence of remaining elements
		largePackagePropsPage.fillPackageDimensions(length.toString(), width.toString(), height.toString());

		// Navigate to the next page
		this.mailServicesPage = largePackagePropsPage.goToMailServicesPage();
	}

	@Then("I verify the price of shipping with Small boxes is less than {int} on the mail services page")
	public void iVerifyThePriceOfShippingWithSmallBoxesIsLessThan(Integer maxWeight)
			throws IllegalStateException, IllegalArgumentException {
		// Wait for page loading
		this.mailServicesPage.waitForPageElement();

		// Verify section header title
		Assert.assertEquals(mailServicesPage.getSectionHeaderElement().getText(),
				MailServicesPage.MAILSERVICES_SECTION_HEADER);

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = mailServicesPage.getDefaultElements();
		Assert.assertEquals(defaultElements.size(), 4);

		Assert.assertTrue(verifyPkgPrice(), "The Retail ground postage price lesser than $80");
	}

	@Then("I verify the price of shipping with Medium boxes is less than {int} on the mail services page")
	public void iVerifyThePriceOfShippingWithMediumBoxesIsLessThan(Integer maxWeight)
			throws IllegalStateException, IllegalArgumentException {
		// Wait for page loading
		this.mailServicesPage.waitForPageElement();

		// Verify section header title
		Assert.assertEquals(mailServicesPage.getSectionHeaderElement().getText(),
				MailServicesPage.MAILSERVICES_SECTION_HEADER);

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = mailServicesPage.getDefaultElements();
		Assert.assertEquals(defaultElements.size(), 4);

		Assert.assertTrue(verifyPkgPrice(), "The Retail ground postage price lesser than $80");
	}

	@Then("I verify the price of shipping with Large boxes is less than {int} on the mail services page")
	public void iVerifyThePriceOfShippingWithLargeBoxesIsLessThan(Integer maxWeight)
			throws IllegalStateException, IllegalArgumentException {
		// Wait for page loading
		this.mailServicesPage.waitForPageElement();

		// Verify section header title
		Assert.assertEquals(mailServicesPage.getSectionHeaderElement().getText(),
				MailServicesPage.MAILSERVICES_SECTION_HEADER);

		// Verify default elements' values
		ArrayList<WebElement> defaultElements = mailServicesPage.getDefaultElements();
		Assert.assertEquals(defaultElements.size(), 4);

		// Verify the retail price
		Assert.assertTrue(verifyPkgPrice(), "The Retail ground postage price lesser than $80");
	}

	public boolean verifyPkgPrice() throws IllegalStateException, IllegalArgumentException {
		TestHooks.context.getWait().until(ExpectedConditions.visibilityOf(mailServicesPage.getPkgPrice()));
		boolean result = false;
		try {
			result = mailServicesPage.getRetailPrice() < 80;
		} catch (IllegalStateException e1) {
			System.out.println("No match has been attempted!");
		} catch (IllegalArgumentException e2) {
			System.out.println("No capturing group found for this pattern!");
		}
		return result;
	}

}
