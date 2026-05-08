package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.Cart_persistence_Page;

public class Scenario6 {

    Cart_persistence_Page cartPage = new Cart_persistence_Page(Hooks.driver);

    @Then("Click Add to cart for any 2 different products")
    public void clickAddToCartForAnyDifferentProducts() throws InterruptedException {
        cartPage.addTwoProducts();
    }

    @Then("Click on one of the products to go to its detail page,then click Back to products to return")
    public void clickOnOneOfTheProductsToGoToItsDetailPageThenClickBackToProductsToReturn() throws InterruptedException {
        cartPage.clickOneProductAndReturn();
    }

    @Then("Assert that the cart badge still shows 2")
    public void assertThatTheCartBadgeStillShows() {
        cartPage.verifyBadgeCount();
    }

    @Then("go to cart page and click remove for any one of the item")
    public void goToCartPageAndClickRemoveForAnyOneOfTheItem() throws InterruptedException {
        cartPage.goToCartAndRemoveOne();
    }

    @Then("assert the remaining item the one that you did not remove")
    public void assertTheRemainingItemTheOneThatYouDidNotRemove() {
        cartPage.verifyRemainingItem();
    }

    @And("verify if the details like name and price are displayed")
    public void verifyIfTheDetailsLikeNameAndPriceAreDisplayed() {
        cartPage.verifyDetailsVisible();
    }
}