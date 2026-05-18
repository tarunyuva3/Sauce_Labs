package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout_Page {
    WebDriver driver;
    WebDriverWait wait;

    public Checkout_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement backpackAddBtn;

    @FindBy(className = "shopping_cart_link")
    WebElement cartBtn;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement zipCodeField;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(className = "complete-header")
    WebElement confirmationMsg;

    public void addBackpack() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddBtn)).click();
    }

    public void goToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void fillInformation(String fname, String lname, String zip) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(fname);
        lastNameField.sendKeys(lname);
        zipCodeField.sendKeys(zip);
        continueBtn.click();
    }

    public void captureTotal() {
        wait.until(ExpectedConditions.visibilityOf(totalLabel));
        String totalText = totalLabel.getText();
        System.out.println("Captured Total: " + totalText);
        Assert.assertTrue(totalText.contains("Total:"));
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public void verifyConfirmation(String msg) {
        wait.until(ExpectedConditions.visibilityOf(confirmationMsg));
        String actualMsg = confirmationMsg.getText();
        Assert.assertEquals(msg, actualMsg);
        System.out.println("Order Completed: " + actualMsg);
    }
}