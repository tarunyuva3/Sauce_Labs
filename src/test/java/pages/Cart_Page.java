package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Cart_Page {
    public WebDriver driver;
    private List<String> addedItemNames = new ArrayList<>();
    public static List<String> selectedProducts = new ArrayList<>();
    private WebDriverWait wait;

    public Cart_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement firstProductAdd;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement secondProductAdd;

    @FindBy(id = "item_4_title_link")
    WebElement firstProductName;

    @FindBy(id = "item_0_title_link")
    WebElement secondProductName;

    @FindBy(id = "back-to-products")
    WebElement backBtn;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement removeBtn;

    @FindBy(className = "inventory_item_price")
    WebElement remainingItemPrice;

    @FindBy(className = "inventory_item_name")
    WebElement remainingItemName;

    @FindBy(className = "btn_inventory")
    List<WebElement> addButtons;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    public void addTopThreeItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(inventoryNames));
        for (int i = 0; i < 3; i++) {
            addedItemNames.add(inventoryNames.get(i).getText());
            driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
        }
    }

    public void verifyBadgeIsThree() {
        wait.until(ExpectedConditions.textToBePresentInElement(cartBadge, "3"));
        Assert.assertEquals("Badge is not 3!", "3", cartBadge.getText());
    }

    public void removeTopItem() {
        if (!addedItemNames.isEmpty()) {
            addedItemNames.remove(0);
        }
        driver.findElement(By.xpath("(//button[text()='Remove'])[1]")).click();
    }

    public void verifyBadgeIsTwo() {
        wait.until(ExpectedConditions.textToBePresentInElement(cartBadge, "2"));
        Assert.assertEquals("Badge is not 2!", "2", cartBadge.getText());
    }

    public void verifyFinalCartContents() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItemsList));
        Assert.assertEquals("Cart size is not 2!", 2, cartItemsList.size());
        List<String> actualNamesInCart = new ArrayList<>();
        for (WebElement element : namesInsideCart) {
            actualNamesInCart.add(element.getText());
        }
        Assert.assertEquals("Item names do not match!", addedItemNames, actualNamesInCart);
    }

    public void addTwoProducts() {
        wait.until(ExpectedConditions.visibilityOf(firstProductName));
        selectedProducts.clear();
        selectedProducts.add(firstProductName.getText());
        selectedProducts.add(secondProductName.getText());
        firstProductAdd.click();
        secondProductAdd.click();
        System.out.println("Added: " + selectedProducts.get(0) + " and " + selectedProducts.get(1));
    }

    public void clickOneProductAndReturn() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductName)).click();
        wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
    }

    public void verifyBadgeCount() {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        Assert.assertEquals(2, Integer.parseInt(cartBadge.getText()));
        System.out.println("Cart badge count verified: " + cartBadge.getText());
    }

    public void goToCartAndRemoveOne() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        System.out.println("Removed one item from cart.");
    }

    public void verifyRemainingItem() {
        wait.until(ExpectedConditions.visibilityOf(remainingItemName));
        String actualName = remainingItemName.getText();
        Assert.assertEquals(selectedProducts.get(1), actualName);
        System.out.println("Remaining item verified: " + actualName);
    }

    public void verifyDetailsVisible() {
        wait.until(ExpectedConditions.visibilityOf(remainingItemName));
        Assert.assertTrue("Name not displayed", remainingItemName.isDisplayed());
        Assert.assertTrue("Price not displayed", remainingItemPrice.isDisplayed());
        System.out.println("Details validation successful inside cart scope.");
    }

    public void addMultipleItems(int count) {
        wait.until(ExpectedConditions.visibilityOfAllElements(addButtons));
        for (int i = 0; i < count; i++) {
            addButtons.get(i).click();
            System.out.println("Item " + (i + 1) + " added to cart.");
        }
    }

    public void verifyCartBadgeNotPresent() {
        System.out.println("Checking if cart is empty for current user...");

        // Direct DOM lookup using findElements.
        // If empty, it returns a list of size 0 instantly without throwing exceptions.
        List<WebElement> badgeList = driver.findElements(By.className("shopping_cart_badge"));

        // Assert that the list is empty (meaning no badge is present on screen)
        Assert.assertTrue("Cart is NOT empty! Items were leaked from the previous session.", badgeList.isEmpty());
        System.out.println("Success: Cart is verified empty.");
    }

    public void performLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        System.out.println("Logged out successfully.");
    }
}