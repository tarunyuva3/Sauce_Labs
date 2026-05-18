package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Products_Page {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> prices;

    public Products_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(css = ".inventory_item_img img")
    List<WebElement> productImages;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_details_name")
    WebElement detailPageName;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']")
    WebElement boltTshirtLink;

    @FindBy(className = "inventory_details_name")
    WebElement detailName;

    @FindBy(className = "inventory_details_desc")
    WebElement detailDesc;

    @FindBy(className = "inventory_details_price")
    WebElement detailPrice;

    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCartBtn;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(id = "back-to-products")
    WebElement backBtn;

    @FindBy(className = "title")
    WebElement pageTitle;

    public void sortByPriceLowToHigh() {
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_price")));
    }

    public void collectpricesintolist() {
        prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price")));
    }

    public void verifyPricesLowToHigh() {
        List<Double> numericPrices = new ArrayList<>();
        for (WebElement price : prices) {
            numericPrices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        for (int i = 0; i < numericPrices.size() - 1; i++) {
            Assert.assertTrue("Prices not in ascending order", numericPrices.get(i) <= numericPrices.get(i + 1));
        }
        System.out.println("all prices are in ascending order");
        for(double price:numericPrices)
        {
            System.out.println(price);
        }
    }

    public void sortByNameZtoA() {
        Select select = new Select(sortDropdown);
        select.selectByValue("za");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        System.out.println("Sorted By reverse alphabetical order");
    }

    public void verifyNamesZtoA() {
        List<WebElement> names = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        List<String> actualNames = new ArrayList<>();
        for (WebElement name : names) {
            actualNames.add(name.getText());
        }
        for (int i = 0; i < actualNames.size() - 1; i++) {
            Assert.assertTrue("Names not in reverse alphabetical order", actualNames.get(i).compareTo(actualNames.get(i + 1)) >= 0);
        }
        for(String name:actualNames)
        {
            System.out.println(name);
        }
        System.out.println("All names are sorted from Z to A");
    }

    public void verifyImagesNotBroken() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productImages));
        for (WebElement img : productImages) {
            String src = img.getAttribute("src");
            Assert.assertTrue("Image src is missing or empty", src != null && !src.isEmpty());
            Assert.assertFalse("Broken image detected (points to 404)", src.contains("sl-404"));
        }
        System.out.println("Verified " + productImages.size() + " images.");
    }

    public void verifyAltTextMatches() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i).getText();
            String altText = productImages.get(i).getAttribute("alt");
            Assert.assertEquals("Alt text doesn't match product name", productName, altText);
        }
        System.out.println("All Alt text matches successfully.");
    }

    public void clickImageByName(String targetName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equalsIgnoreCase(targetName)) {
                productImages.get(i).click();
                break;
            }
        }
    }

    public void verifyDetailPageTitle(String expectedName) {
        wait.until(ExpectedConditions.visibilityOf(detailPageName));
        String actualName = detailPageName.getText();
        Assert.assertEquals(expectedName, actualName);
        System.out.println("Successfully landed on details page for: " + actualName);
    }

    public void clickBoltTshirt() {
        wait.until(ExpectedConditions.elementToBeClickable(boltTshirtLink)).click();
    }

    public void verifyProductDetails(String expectedName) {
        wait.until(ExpectedConditions.visibilityOf(detailName));
        Assert.assertTrue("Name not displayed", detailName.isDisplayed());
        Assert.assertTrue("Description not displayed", detailDesc.isDisplayed());
        Assert.assertTrue("Price not displayed", detailPrice.isDisplayed());
        String actualName = detailName.getText();
        Assert.assertEquals("Product name mismatch!", expectedName, actualName);
        System.out.println("Verified details for: " + actualName);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public void checkBadge() {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        String count = cartBadge.getText();
        Assert.assertEquals("1", count);
        System.out.println("Cart badge shows: " + count);
    }

    public void goBackAndVerify() {
        wait.until(ExpectedConditions.elementToBeClickable(backBtn)).click();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = pageTitle.getText();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
        Assert.assertEquals("Products", currentTitle);
        System.out.println("Back on product page. Title: " + currentTitle);
    }
}