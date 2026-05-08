package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.Cross_user_Page;
import pages.Login_Page; // Ensure this matches your login page class name

public class Scenario10 {

    Cross_user_Page cartPage = new Cross_user_Page(Hooks.driver);
    Login_Page loginPage = new Login_Page(Hooks.driver);

    @And("add {int} items to the cart")
    public void addItemsToTheCart(int count) {
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


