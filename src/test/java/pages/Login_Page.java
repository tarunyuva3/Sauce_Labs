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

public class Login_Page {
    public WebDriver driver;
    public WebDriverWait wait;

    public Login_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement usernameInput;

    @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement login_butt;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    public void username_field(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).click();
        usernameInput.sendKeys(username);
    }

    public void password_field(String Password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
        passwordInput.sendKeys(Password);
    }

    public void login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(login_butt)).click();
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(user);
        passwordInput.sendKeys(pass);
        loginBtn.click();
    }

    public void waitForProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void logoutUser() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void verifyLoginRedirection() {
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();
        Assert.assertEquals("https://www.saucedemo.com/", currentUrl);
        Assert.assertEquals("Swag Labs", title);
        System.out.println("Logout successful. Current URL: " + currentUrl);
    }

    public void manuallyNavigateTo(String url) {
        System.out.println("Attempting to bypass login by navigating to: " + url);
        driver.get(url);
    }

    public void verifySessionBlocked() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://www.saucedemo.com/"),
                ExpectedConditions.urlContains("error")
        ));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals("https://www.saucedemo.com/") || currentUrl.contains("error"));
        System.out.println("Access denied. Direct navigation blocked.");
    }
}