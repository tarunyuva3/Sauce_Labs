package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Visual_verification_Page {
    WebDriver driver;

    public Visual_verification_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Target the img tags directly to avoid child-element lookup errors
    @FindBy(css = ".inventory_item_img img")
    List<WebElement> productImages;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_details_name")
    WebElement detailPageName;

    public void verifyImagesNotBroken()
    {
        // Add a small delay for images to render
        for (WebElement img : productImages) {
            String src = img.getAttribute("src");

            // Check if src is present and not pointing to the 404 placeholder
            Assert.assertTrue("Image src is missing or empty", src != null && !src.isEmpty());
            Assert.assertFalse("Broken image detected (points to 404)", src.contains("sl-404"));
        }
        System.out.println("Verified " + productImages.size() + " images.");
    }

    public void verifyAltTextMatches() {
        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i).getText();
            String altText = productImages.get(i).getAttribute("alt");

            Assert.assertEquals("Alt text doesn't match product name", productName, altText);
        }
        System.out.println("All Alt text matches successfully.");
    }

    public void clickImageByName(String targetName) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equalsIgnoreCase(targetName)) {
                productImages.get(i).click();
                break;
            }
        }
    }

    public void verifyDetailTitle(String expectedName) {
        Assert.assertEquals(expectedName, detailPageName.getText());
    }
}