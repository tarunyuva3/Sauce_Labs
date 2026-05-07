package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class Cart_Page {
    public WebDriver driver;
    // List to store names of the 3 items added
    private List<String> addedItemNames = new ArrayList<>();

    public Cart_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryNames;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(className = "cart_item")
    List<WebElement> cartItemsList;

    @FindBy(className = "inventory_item_name")
    List<WebElement> namesInsideCart;

    public void addTopThreeItems()
    {
        for (int i = 0; i < 3; i++)
        {
            // Save name before clicking
            addedItemNames.add(inventoryNames.get(i).getText());
            // Click the first available "Add to cart" button
            driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
        }
    }

    public void verifyBadgeIsThree()
    {
        Assert.assertEquals("Badge is not 3!", "3", cartBadge.getText());
    }

    public void removeTopItem()
    {
        // Remove first name from our tracking list because we are removing it from UI
        if (!addedItemNames.isEmpty()) {
            addedItemNames.remove(0);
        }
        driver.findElement(By.xpath("(//button[text()='Remove'])[1]")).click();

    }

    public void verifyBadgeIsTwo()
    {
        Assert.assertEquals("Badge is not 2!", "2", cartBadge.getText());
    }

    public void verifyFinalCartContents()
    {
        // 1. Check if total count is 2
        Assert.assertEquals("Cart size is not 2!", 2, cartItemsList.size());

        // 2. Normal loop to collect names from Cart Page
        List<String> actualNamesInCart = new ArrayList<>();
        for (WebElement element : namesInsideCart) {
            actualNamesInCart.add(element.getText());
        }

        // 3. Compare tracked names with actual names in cart
        Assert.assertEquals("Item names do not match!", addedItemNames, actualNamesInCart);
        System.out.println("Verified: The correct 2 items are in the cart.");
        for (String t : actualNamesInCart)
        {
            System.out.println(t);
        }

    }
}