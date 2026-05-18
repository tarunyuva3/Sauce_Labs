package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Products_Page;

public class Scenario9 {

    Products_Page productPage;

    @Then("find all product images and verify they are not broken")
    public void findAllProductImagesAndVerifyTheyAreNotBroken() {
        productPage = new Products_Page(Hooks.driver);
        productPage.verifyImagesNotBroken();
    }

    @And("check that image alt text matches product name")
    public void checkThatImageAltTextMatchesProductName() {
        productPage.verifyAltTextMatches();
    }

    @When("click on the image of {string}")
    public void clickOnTheImageOf(String productName) {
        productPage.clickImageByName(productName);
    }

    @Then("verify detail page title matches {string}")
    public void verifyDetailPageTitleMatches(String expectedName) {
        productPage.verifyDetailPageTitle(expectedName);
    }

    @And("print current page URL and title")
    public void printCurrentPageURLAndTitle() {
        System.out.println("Final Page Title: " + Hooks.driver.getTitle());
        System.out.println("Final Page URL: " + Hooks.driver.getCurrentUrl());
    }
}