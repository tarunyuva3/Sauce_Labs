package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Performance_Page;

public class Scenario8 {

    Performance_Page perfPage = new Performance_Page(Hooks.driver);
    long startTime;
    long glitchTime;
    long standardTime;

    @Given("record start time")
    public void recordStartTime() {
        startTime = System.currentTimeMillis();
    }

    @When("login as {string} with {string}")
    public void loginAsWith(String user, String pass) {
        perfPage.login(user, pass);
    }

    @Then("wait for products page and calculate load time")
    public void waitForProductsPageAndCalculateLoadTime() {
        perfPage.waitForProducts();
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
        perfPage.logout();
        // Reset start time for standard user
        startTime = System.currentTimeMillis();
        perfPage.login(user, pass);
    }

    @And("compare both load times for performance difference")
    public void compareBothLoadTimesForPerformanceDifference() {
        perfPage.waitForProducts();
        long endTime = System.currentTimeMillis();
        standardTime = (endTime - startTime) / 1000;

        System.out.println("Standard User Load Time: " + standardTime + " seconds");
        System.out.println("Time Difference: " + (glitchTime - standardTime) + " seconds");

        // Assert that standard user is indeed faster than the glitch user
        Assert.assertTrue("Performance user should be slower than standard user", standardTime <= glitchTime);
    }
}