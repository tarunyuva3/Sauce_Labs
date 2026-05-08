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

public class Performance_Page {
    WebDriver driver;
    WebDriverWait wait;

    public Performance_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    public void login(String user, String pass) {
        userName.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }

    public void waitForProducts() {
        // Wait for the 'Products' header to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
    }

    public void logout() throws InterruptedException {
        menuBtn.click();
        // Wait for the sidebar animation before clicking logout
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }
}