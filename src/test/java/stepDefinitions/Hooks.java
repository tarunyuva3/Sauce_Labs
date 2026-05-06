package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Hooks
{
    public static WebDriver driver;
    @Before
    public void setup()
    {
        ChromeOptions options = new ChromeOptions();

        // Create a Map to store preferences
        Map<String, Object> prefs = new HashMap<String, Object>();

        // This specific line disables the 'Data Breach' popup
        prefs.put("profile.password_manager_leak_detection", false);

        // You might also want to disable the general password manager save prompt
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
    @After
    public void breakdown()
    {
        driver.quit();
    }
}
