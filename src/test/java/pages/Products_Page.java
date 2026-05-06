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

public class Products_Page
{
    public WebDriver driver;
    public WebDriverWait wait;
    List<Double> numeric_prices;
    Select sel;

    public Products_Page(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement sort;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> priceList;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement newSort;

    @FindBy(xpath ="//div[@class='inventory_item_name']")
    List<WebElement> rev;

    //clicking sort button
    public void click_sort()
    {
        wait.until(ExpectedConditions.elementToBeClickable(sort)).click();
    }
    public void apply_filter_Price_low_to_high()
    {
        sel= new Select(sort);
        sel.selectByValue("lohi");

    }

   //stored all prices in the list (Low to High)
    public void store_all_prices_in_list()
    {
        numeric_prices= new ArrayList<>();
        for(WebElement prices_element : priceList)
        {
            String prices_text=prices_element.getText().replace("$","");
            numeric_prices.add(Double.parseDouble(prices_text));
            System.out.println("Found price: " + prices_text);
        }
    }

    //checking all the prices are sorted correctly
    public void check_all_prices_of_low_to_high()
    {
        for (int i = 0; i < numeric_prices.size() - 1; i++)
        {
            double current = numeric_prices.get(i);
            double next = numeric_prices.get(i + 1);

            if (current > next)
            {
                Assert.fail("Prices are NOT in ascending order! Found " + current + " before " + next);
            }
        }
    }

    //now select Name(Z to A) in the sort option and check whether the names are in reverse alphabetical order
    public void reverse_alphabetical_order()
    {

        // 1. Wait for the old sort element (which was used for 'lohi') to become stale
        wait.until(ExpectedConditions.stalenessOf(sort));

        // 2. Find the fresh sort element after the page update
        //WebElement newSort = driver.findElement(By.xpath("//select[@class='product_sort_container']"));

        // 3. Create a new Select object with the fresh reference
        Select sc = new Select(newSort);

        // 4. Perform the new selection
        sc.selectByValue("za");

        System.out.println("Successfully changed sort to Name (Z to A).");

        List<String> actualNames = new ArrayList<>();

        for (WebElement element : rev) {
            actualNames.add(element.getText());
            System.out.println("Product Name: " + element.getText());
        }

        // 2. Verify descending order
        for (int i = 0; i < actualNames.size() - 1; i++) {
            String current = actualNames.get(i);
            String next = actualNames.get(i + 1);

            // compareTo returns < 0 if current is alphabetically before next
            if (current.compareTo(next) < 0) {
                Assert.fail("Names are NOT in descending order! Found '" + current + "' before '" + next + "'");
            }
        }

        System.out.println("Assertion Passed: Names are in reverse alphabetical order.");
    }

}




