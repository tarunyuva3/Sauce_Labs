package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Products_Page;

public class Scenario5 {

    Products_Page productPage;

    @When("you click on a product name {string} it should take you to its page")
    public void youClickOnAProductNameItShouldTakeYouToItsPage(String productName) throws InterruptedException
    {
        productPage = new Products_Page(Hooks.driver);
        productPage.clickBoltTshirt();
    }

    @And("check whether all details name price and description are visible and also check if its the same product")
    public void checkWhetherAllDetailsNamePriceAndDescriptionAreVisibleAndAlsoCheckIfItsTheSameProduct() throws InterruptedException
    {
        productPage.verifyProductDetails("Sauce Labs Bolt T-Shirt");
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