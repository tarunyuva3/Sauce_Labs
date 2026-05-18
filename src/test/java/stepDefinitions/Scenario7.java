package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.Login_Page;

public class Scenario7 {

    Login_Page loginPage;

    @Then("click on three horizintal lines left side and click logout")
    public void clickOnThreeHorizintalLinesLeftSideAndClickLogout() throws InterruptedException {
        loginPage = new Login_Page(Hooks.driver);
        loginPage.logoutUser();
    }

    @Then("assert that you are back on homepage by checking url and Title")
    public void assertThatYouAreBackOnHomepageByCheckingUrlAndTitle() {
        loginPage.verifyLoginRedirection();
    }

    @Then("in the address bar manually type {string} and click enter")
    public void inTheAddressBarManuallyTypeAndClickEnter(String url) throws InterruptedException {
        loginPage.manuallyNavigateTo(url);
    }

    @Then("assert that app automatically redirects you to login page instead of products page")
    public void assertThatAppAutomaticallyRedirectsYouToLoginPageInsteadOfProductsPage() {
        loginPage.verifySessionBlocked();
    }
}