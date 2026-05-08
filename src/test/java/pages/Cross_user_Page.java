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
import java.util.List;

public class Cross_user_Page {
    WebDriver driver;
    WebDriverWait wait;

    public Cross_user_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "btn_inventory")
    List<WebElement> addButtons;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    public void addMultipleItems(int count) {
        for (int i = 0; i < count; i++) {
            addButtons.get(i).click();
            System.out.println("Item " + (i + 1) + " added to cart.");
            try { Thread.sleep(1500); } catch (InterruptedException e) {} // Pause to see the badge update
        }
    }

    public void verifyCartBadgeNotPresent() {
        System.out.println("Checking if cart is empty for current user...");
        try { Thread.sleep(3000); } catch (InterruptedException e) {} // Long pause to observe the empty cart icon

        // Direct fetch to ensure we see exactly what's on screen right now
        List<WebElement> currentBadge = driver.findElements(By.className("shopping_cart_badge"));

        Assert.assertTrue("Cart is NOT empty! Items were leaked from the previous session.", currentBadge.isEmpty());
        System.out.println("Success: Cart is verified empty.");
    }

    public void performLogout() {
        try {
            menuBtn.click();
            Thread.sleep(1500); // Watch the menu slide open
            wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
            System.out.println("Logged out successfully.");
            Thread.sleep(1500); // Watch it return to login page
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}