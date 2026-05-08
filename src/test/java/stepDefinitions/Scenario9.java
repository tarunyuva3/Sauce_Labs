package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Visual_verification_Page;

public class Scenario9 {

    Visual_verification_Page visualPage = new Visual_verification_Page(Hooks.driver);

    @Then("find all product images and verify they are not broken")
    public void findAllProductImagesAndVerifyTheyAreNotBroken() {
        visualPage.verifyImagesNotBroken();
    }

    @And("check that image alt text matches product name")
    public void checkThatImageAltTextMatchesProductName() {
        visualPage.verifyAltTextMatches();
    }

    @When("click on the image of {string}")
    public void clickOnTheImageOf(String productName) {
        visualPage.clickImageByName(productName);
    }

    @Then("verify detail page title matches {string}")
    public void verifyDetailPageTitleMatches(String expectedName) {
        visualPage.verifyDetailTitle(expectedName);
    }

    @And("print current page URL and title")
    public void printCurrentPageURLAndTitle() {
        System.out.println("Final Page Title: " + Hooks.driver.getTitle());
        System.out.println("Final Page URL: " + Hooks.driver.getCurrentUrl());
    }
}