package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class End_to_End_Page
{
    WebDriver driver;

    public End_to_End_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
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

    // Actions
    public void addBackpack() throws InterruptedException
    {
        backpackAddBtn.click();
        Thread.sleep(2000);
    }

    public void goToCheckout() throws InterruptedException
    {
        cartBtn.click();
        Thread.sleep(1000);
        checkoutBtn.click();
        Thread.sleep(1000);
    }

    public void fillInformation(String fname, String lname, String zip) throws InterruptedException
    {
        firstNameField.sendKeys(fname);
        lastNameField.sendKeys(lname);
        zipCodeField.sendKeys(zip);
        Thread.sleep(1000);
        continueBtn.click();
        Thread.sleep(2000);
    }

    public void captureTotal()
    {
        String totalText = totalLabel.getText();
        System.out.println("Captured Total: " + totalText);
        Assert.assertTrue(totalText.contains("Total:"));
    }

    public void finishAndVerify(String expectedMsg) throws InterruptedException
    {
        finishBtn.click();
        Thread.sleep(2000);
        String actualMsg = confirmationMsg.getText();
        Assert.assertEquals(expectedMsg, actualMsg);
    }
}