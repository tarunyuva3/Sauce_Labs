package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login_Page;

import static stepDefinitions.Hooks.driver;

public class Scenario1 {

    // Initialize Login_Page using the static driver from your Hooks class
    Login_Page login;

    @Given("Open the Website and website and you can see the username,password and login button")
    public void openingpage()
    {
        login = new Login_Page(driver);
        // Simple console verification to ensure the driver is on the correct page
        System.out.println("Page Title: " + driver.getTitle());
    }

    @When("click the login button without entering any username or password")
    public void justloginbuttonpress() {
        login.login_button();
        driver.navigate().refresh();
    }

    @And("Type {string} in the username field then type wrong password {string} click login you should see a error message")
    public void invalid_credentials_check(String username, String password) {
        login.username_field(username);
        login.password_field(password);
        login.login_button();
        driver.navigate().refresh();
    }

    @When("try {string} for locked out user and correct password {string} then it will show as locked user")
    public void try_for_locked_out_user_and_correct_password_then_it_will_show_as_locked_user(String user, String pass) {
        login.username_field(user);
        login.password_field(pass);
        login.login_button();
        driver.navigate().refresh();
    }

    @Then("enter correct {string} and {string} then login")
    public void successful_login(String username, String password) {
        login.username_field(username);
        login.password_field(password);
        login.login_button();
    }
}