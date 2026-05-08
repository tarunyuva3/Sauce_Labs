package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout_validation_Page {
    WebDriver driver;

    public Logout_validation_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    public void logoutUser() throws InterruptedException {
        menuButton.click();
        Thread.sleep(1500); // Wait for sidebar animation
        logoutLink.click();
        Thread.sleep(1000);
    }

    public void verifyLoginRedirection() {
        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        Assert.assertEquals("https://www.saucedemo.com/", currentUrl);
        Assert.assertEquals("Swag Labs", title);
        System.out.println("Logout successful. Current URL: " + currentUrl);
    }

    public void manuallyNavigateTo(String url) throws InterruptedException {
        System.out.println("Attempting to bypass login by navigating to: " + url);
        driver.get(url);
        Thread.sleep(2000);
    }

    public void verifySessionBlocked() {
        String currentUrl = driver.getCurrentUrl();
        // The app should kick us back to the home/login page
        Assert.assertEquals("https://www.saucedemo.com/", currentUrl);
        System.out.println("Session validation passed. Redirected back to: " + currentUrl);
    }
}