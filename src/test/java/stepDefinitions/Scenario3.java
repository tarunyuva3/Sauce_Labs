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

    public WebDriver driver = Hooks.driver;

    // Initializing Page Objects
    Cart_Page cartPage = new Cart_Page(driver);
    Login_Page login = new Login_Page(driver);

    @Given("login with correct credentials username{string} and password{string}")
    public void loginWithCorrectCredentialsUsernameAndPassword(String user, String pass)
    {
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
        // Click the cart icon
        driver.findElement(By.className("shopping_cart_link")).click();
        // Verify final 2 items count and names (hardcoded logic)
        cartPage.verifyFinalCartContents();
    }
}