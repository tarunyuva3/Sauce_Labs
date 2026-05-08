package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.Logout_validation_Page;

public class Scenario7 {

    Logout_validation_Page logoutPage = new Logout_validation_Page(Hooks.driver);

    @Then("click on three horizintal lines left side and click logout")
    public void clickOnThreeHorizintalLinesLeftSideAndClickLogout() throws InterruptedException {
        logoutPage.logoutUser();
    }

    @Then("assert that you are back on homepage by checking url and Title")
    public void assertThatYouAreBackOnHomepageByCheckingUrlAndTitle() {
        logoutPage.verifyLoginRedirection();
    }

    @Then("in the address bar manually type {string} and click enter")
    public void inTheAddressBarManuallyTypeAndClickEnter(String url) throws InterruptedException {
        logoutPage.manuallyNavigateTo(url);
    }

    @Then("assert that app automatically redirects you to login page instead of products page")
    public void assertThatAppAutomaticallyRedirectsYouToLoginPageInsteadOfProductsPage() {
        logoutPage.verifySessionBlocked();
    }
}