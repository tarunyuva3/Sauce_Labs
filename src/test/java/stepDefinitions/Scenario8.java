package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Login_Page;

public class Scenario8 {

    Login_Page loginPage;
    long startTime;
    long glitchTime;
    long standardTime;

    @Given("record start time")
    public void recordStartTime() {
        startTime = System.currentTimeMillis();
    }

    @When("login as {string} with {string}")
    public void loginAsWith(String user, String pass) {
        loginPage = new Login_Page(Hooks.driver);
        loginPage.username_field(user);
        loginPage.password_field(pass);
        loginPage.login_button();
    }

    @Then("wait for products page and calculate load time")
    public void waitForProductsPageAndCalculateLoadTime() {
        loginPage.waitForProducts();
        long endTime = System.currentTimeMillis();
        glitchTime = (endTime - startTime) / 1000;
        System.out.println("Glitch User Load Time: " + glitchTime + " seconds");
    }

    @And("verify load time is under 10 seconds")
    public void verifyLoadTimeIsUnderSeconds() {
        Assert.assertTrue("Page load is too slow! Took: " + glitchTime, glitchTime < 10);
    }

    @Then("logout and login as {string} and {string}")
    public void logoutAndLoginAsAnd(String user, String pass) throws InterruptedException {
        loginPage.logout();
        startTime = System.currentTimeMillis(); // Reset start time
        loginPage.username_field(user);
        loginPage.password_field(pass);
        loginPage.login_button();
    }

    @And("compare both load times for performance difference")
    public void compareBothLoadTimesForPerformanceDifference() {
        loginPage.waitForProducts();
        long endTime = System.currentTimeMillis();
        standardTime = (endTime - startTime) / 1000;

        System.out.println("Standard User Load Time: " + standardTime + " seconds");
        System.out.println("Time Difference: " + (glitchTime - standardTime) + " seconds");
        Assert.assertTrue("Performance user logic mismatch", glitchTime >= standardTime);
    }
}