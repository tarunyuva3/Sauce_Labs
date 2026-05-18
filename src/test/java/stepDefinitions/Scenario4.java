package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.Checkout_Page;

public class Scenario4 {
    WebDriver driver;
    Checkout_Page checkoutPage;

    @When("logged in successfully find Sauce Labs Backpack and add it to cart")
    public void loggedInSuccessfullyFindSauceLabsBackpackAndAddItToCart() throws InterruptedException
    {
        driver = Hooks.driver;
        checkoutPage = new Checkout_Page(driver);
        checkoutPage.addBackpack();
    }

    @And("click on cart button and click checkout")
    public void clickOnCartButtonAndClickCheckout() throws InterruptedException
    {
        checkoutPage.goToCheckout();
    }

    @And("Fill the form with First Name {string} Last Name {string} and a Zip Code {string}.Then click continue")
    public void fillTheFormWithFirstNameLastNameAndAZipCodeThenClickContinue(String fName, String lName, String zip) throws InterruptedException
    {
        checkoutPage.fillInformation(fName, lName, zip);
    }

    @Then("check for the total amount and capture it")
    public void checkForTheTotalAmountAndCaptureIt()
    {
        checkoutPage.captureTotal();
    }

    @Then("click finish assert it shows {string}")
    public void clickFinishAssertItShows(String expectedMessage) throws InterruptedException
    {
        checkoutPage.clickFinish();
        checkoutPage.verifyConfirmation(expectedMessage);
    }
}