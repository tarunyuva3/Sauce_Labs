package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class Cart_persistence_Page {
    WebDriver driver;
    public static List<String> selectedProducts = new ArrayList<>();

    public Cart_persistence_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Using ID or simpler class structures often works better in SauceLabs
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement firstProductAdd;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement secondProductAdd;

    @FindBy(id = "item_4_title_link")
    WebElement firstProductName;

    @FindBy(id = "item_0_title_link")
    WebElement secondProductName;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(id = "back-to-products")
    WebElement backBtn;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement removeBtn;

    @FindBy(className = "inventory_item_name")
    WebElement remainingItemName;

    @FindBy(className = "inventory_item_price")
    WebElement remainingItemPrice;

    public void addTwoProducts() throws InterruptedException {
        // A short sleep to ensure the login transition is finished
        Thread.sleep(2000);

        selectedProducts.clear();
        selectedProducts.add(firstProductName.getText());
        selectedProducts.add(secondProductName.getText());

        firstProductAdd.click();
        secondProductAdd.click();
        Thread.sleep(1000);
        System.out.println("Added: " + selectedProducts.get(0) + " and " + selectedProducts.get(1));
    }

    public void clickOneProductAndReturn() throws InterruptedException {
        firstProductName.click();
        Thread.sleep(1500);
        backBtn.click();
        Thread.sleep(1500);
    }

    public void verifyBadgeCount() {
        Assert.assertEquals(2, Integer.parseInt(cartBadge.getText()));
        System.out.println("Cart badge count verified: " +cartBadge.getText());
    }

    public void goToCartAndRemoveOne() throws InterruptedException {
        cartIcon.click();
        Thread.sleep(1500);
        removeBtn.click();
        Thread.sleep(1500);
        System.out.println("Removed one item from cart.");
    }

    public void verifyRemainingItem() {
        String actualName = remainingItemName.getText();
        // Check if the remaining item is the second one we added
        Assert.assertEquals(selectedProducts.get(1), actualName);
        System.out.println("Remaining item verified: " + actualName);
    }

    public void verifyDetailsVisible() {
        Assert.assertTrue("Name not displayed", remainingItemName.isDisplayed());
        Assert.assertTrue("Price not displayed", remainingItemPrice.isDisplayed());
        System.out.println("Details verified for: " + remainingItemName.getText());
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}