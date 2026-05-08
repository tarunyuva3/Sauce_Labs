package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Product_verification_Page;

public class Scenario5 {

    // Using the driver from your Hooks
    Product_verification_Page productPage = new Product_verification_Page(Hooks.driver);

    @When("you click on a product name {string} it should take you to its page")
    public void youClickOnAProductNameItShouldTakeYouToItsPage(String productName) throws InterruptedException
    {
        productPage.clickBoltTshirt();
    }

    @And("check whether all details name price and description are visible and also check if its the same product")
    public void checkWhetherAllDetailsNamePriceAndDescriptionAreVisibleAndAlsoCheckIfItsTheSameProduct() throws InterruptedException
    {
        // We pass "Sauce Labs Bolt T-Shirt" to verify the name on the detail page
        productPage.verifyDetails("Sauce Labs Bolt T-Shirt");
    }

    @Then("click add to cart")
    public void clickAddToCart() throws InterruptedException
    {
        productPage.addToCart();
    }

    @Then("check the cart badge shows number 1")
    public void checkTheCartBadgeShowsNumber()
    {

        productPage.checkBadge();
    }

    @Then("click Back to products will be redirected to homepage  Assert that the URL and the page heading are correct")
    public void clickBackToProductsWillBeRedirectedToHomepageAssertThatTheURLAndThePageHeadingAreCorrect() throws InterruptedException
    {
        productPage.goBackAndVerify();
    }
}