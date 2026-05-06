package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Page
{
    public WebDriver driver;
    public WebDriverWait wait;

    public Login_Page(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//input[@placeholder='Username']")
    WebElement usernameInput;

    @FindBy(id="password")
    WebElement passwordInput;

    @FindBy(xpath="//input[@id='login-button']")
    WebElement login_butt;

    public void username_field (String username)
    {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).click();
        usernameInput.sendKeys(username);
    }

    public void password_field (String Password)
    {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
        passwordInput.sendKeys(Password);
    }

    public void login_button()
    {
        wait.until(ExpectedConditions.elementToBeClickable(login_butt)).click();
    }

}
