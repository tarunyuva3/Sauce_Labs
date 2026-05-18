package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.Cart_Page;

public class Scenario10 {

    Cart_Page cartPage;

    @And("add {int} items to the cart")
    public void addItemsToTheCart(int count) {
        cartPage = new Cart_Page(Hooks.driver);
        cartPage.addMultipleItems(count);
    }

    @When("logout from the application")
    public void logoutFromTheApplication() {
        cartPage.performLogout();
    }

    @And("verify cart is empty")
    public void verifyCartIsEmpty() {
        cartPage.verifyCartBadgeNotPresent();
    }

    @When("add {int} item to cart and logout")
    public void addItemToCartAndLogout(int count) {
        cartPage.addMultipleItems(count);
        cartPage.performLogout();
    }
}