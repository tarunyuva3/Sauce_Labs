package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.End_to_End_Page;

public class Scenario4 {
    WebDriver driver = Hooks.driver;
    End_to_End_Page e2e = new End_to_End_Page(driver);

    @When("logged in successfully find Sauce Labs Backpack and add it to cart")
    public void loggedInSuccessfullyFindSauceLabsBackpackAndAddItToCart() throws InterruptedException
    {
        e2e.addBackpack();
    }

    @And("click on cart button and click checkout")
    public void clickOnCartButtonAndClickCheckout() throws InterruptedException
    {
        e2e.goToCheckout();
    }

    @And("Fill the form with First Name {string} Last Name {string} and a Zip Code {string}.Then click continue")
    public void fillTheFormWithFirstNameLastNameAndAZipCodeThenClickContinue(String fName, String lName, String zip) throws InterruptedException
    {
        e2e.fillInformation(fName, lName, zip);
    }

    @Then("check for the total amount and capture it")
    public void checkForTheTotalAmountAndCaptureIt()
    {
        e2e.captureTotal();
    }

    @Then("click finish assert it shows {string}")
    public void clickFinishAssertItShows(String expectedMessage) throws InterruptedException
    {
        e2e.finishAndVerify(expectedMessage);
    }
}