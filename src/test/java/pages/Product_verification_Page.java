package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_verification_Page
{
    WebDriver driver;

    public Product_verification_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators specifically for Bolt T-Shirt and Details Page
    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']")
    WebElement boltTshirtLink;

    @FindBy(className = "inventory_details_name")
    WebElement detailName;

    @FindBy(className = "inventory_details_desc")
    WebElement detailDesc;

    @FindBy(className = "inventory_details_price")
    WebElement detailPrice;

    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCartBtn;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(id = "back-to-products")
    WebElement backBtn;

    @FindBy(className = "title")
    WebElement pageTitle;

    public void clickBoltTshirt() throws InterruptedException
    {
        boltTshirtLink.click();
        Thread.sleep(2000);
    }

    public void verifyDetails(String expectedName) throws InterruptedException
    {
        // Checking visibility
        Assert.assertTrue("Name not displayed", detailName.isDisplayed());
        Assert.assertTrue("Description not displayed", detailDesc.isDisplayed());
        Assert.assertTrue("Price not displayed", detailPrice.isDisplayed());

        // Verifying it is the same product
        String actualName = detailName.getText();
        Assert.assertEquals("Product name mismatch!", expectedName, actualName);
        System.out.println("Verified details for: " + actualName);
        Thread.sleep(2000);
    }

    public void addToCart() throws InterruptedException
    {
        addToCartBtn.click();
        Thread.sleep(2000);
    }

    public void checkBadge()
    {
        String count = cartBadge.getText();
        Assert.assertEquals("1", count);
        System.out.println("Cart badge shows: " + count);
    }

    public void goBackAndVerify() throws InterruptedException
    {
        backBtn.click();
        Thread.sleep(2000);

        // Assert URL and Page Heading
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = pageTitle.getText();

        Assert.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
        Assert.assertEquals("Products", currentTitle);
        System.out.println("Back on product page. Title: " + currentTitle);
        System.out.println("Back on product page. url: " + currentUrl);
    }
}