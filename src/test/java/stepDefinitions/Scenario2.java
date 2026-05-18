package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.Login_Page;
import pages.Products_Page;

public class Scenario2
{
    public WebDriver driver;
    Login_Page login;
    Products_Page pd;

    @Given("To Navigate to Sauce Demo Username {string} and Password {string} and click on Name\\(A-Z) dropdown for sorting options")
    public void to_navigate_to_sauce_demo_username_and_password_and_click_on_name_a_z_dropdown_for_sorting_options(String user, String pass)
    {
        driver = Hooks.driver;
        login = new Login_Page(driver);
        pd = new Products_Page(driver);
        login.username_field(user);
        login.password_field(pass);
        login.login_button();
    }
    @When("Name\\(A-Z) is clicked select price \\(Low to High) and click that so items will be rearranged")
    public void name_a_z_is_clicked_select_price_low_to_high_and_click_that_so_items_will_be_rearranged()
    {
        pd.sortByPriceLowToHigh();
    }
    @When("collect all the price mentioned into a list")
    public void collect_all_the_price_mentioned_into_a_list()
    {
        pd.collectpricesintolist();
    }
    @When("Check for the prices in the list whether they are in ascending order lower then then the next one otherwise the test is failed")
    public void check_for_the_prices_in_the_list_whether_they_are_in_ascending_order_lower_then_then_the_next_one_otherwise_the_test_is_failed()
    {
        pd.verifyPricesLowToHigh();
    }

    @Then("click sorting filter Name\\(Z to A ) and verify they are in reverse alphabetical order")
    public void clickSortingFilterNameZToAAndVerifyTheyAreInReverseAlphabeticalOrder()
    {
        pd.sortByNameZtoA();
        pd.verifyNamesZtoA();
    }
}