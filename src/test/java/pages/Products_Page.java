package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Products_Page {

    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> prices;

    public Products_Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    public void sortByPriceLowToHigh()
    {
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");

        // Wait until products refresh
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_item_price")));
    }

    public void collectpricesintolist()
    {
         prices= driver.findElements(
                By.className("inventory_item_price"));
    }

    public void verifyPricesLowToHigh()
    {
        List<Double> numericPrices = new ArrayList<>();

        for (WebElement price : prices) {
            numericPrices.add(
                    Double.parseDouble(price.getText().replace("$", "")));
        }

        for (int i = 0; i < numericPrices.size() - 1; i++) {
            Assert.assertTrue(
                    "Prices not in ascending order",
                    numericPrices.get(i) <= numericPrices.get(i + 1)
            );
        }
        System.out.println("all prices are in ascending order");
    }

    public void sortByNameZtoA()
    {
        Select select = new Select(sortDropdown);
        select.selectByValue("za");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_item_name")));

        System.out.println("Sorted By reverse alphabetical order");
    }

    public void verifyNamesZtoA()
    {
        List<WebElement> names = driver.findElements(
                By.className("inventory_item_name"));

        List<String> actualNames = new ArrayList<>();

        for (WebElement name : names)
        {
            actualNames.add(name.getText());
        }

        for (int i = 0; i < actualNames.size() - 1; i++) {
            Assert.assertTrue(
                    "Names not in reverse alphabetical order",
                    actualNames.get(i).compareTo(
                            actualNames.get(i + 1)) >= 0
            );
        }
        System.out.println("All names are arranged u=in reverse alphabetical order");
    }
}

