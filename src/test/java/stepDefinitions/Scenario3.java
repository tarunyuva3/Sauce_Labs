package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.Cart_Page;
import pages.Login_Page;

public class Scenario3 {

    public WebDriver driver;

    // Initializing Page Objects safely inside methods
    Cart_Page cartPage;
    Login_Page login;

    @Given("login with correct credentials username{string} and password{string}")
    public void loginWithCorrectCredentialsUsernameAndPassword(String user, String pass)
    {
        driver = Hooks.driver;
        cartPage = new Cart_Page(driver);
        login = new Login_Page(driver);
        login.username_field(user);
        login.password_field(pass);
        login.login_button();
    }

    @When("login successfully add 3 items to cart")
    public void loginSuccessfullyAddItemsToCart()
    {
        cartPage.addTopThreeItems();
    }

    @And("check if the number is 3 on cart image")
    public void checkIfTheNumberIs3OnCartImage()
    {
        cartPage.verifyBadgeIsThree();
    }

    @And("remove one item from the cart")
    public void removeOneItemFromTheCart()
    {
        cartPage.removeTopItem();
    }

    @And("check that the badge number shows 2 now")
    public void checkThatTheBadgeNumberShowsNow()
    {
        cartPage.verifyBadgeIsTwo();
    }

    @Then("click on cart page and assert the items you added")
    public void clickOnCartPageAndAssertTheItemsYouAdded()
    {
        cartPage.verifyFinalCartContents();
    }
}