package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void initDriver()
    {
        if (tlDriver.get() != null)
        {
            return;
        }

        ConfigReader.loadConfig();

        String execution = ConfigReader.getProperty("Execution");

        if (execution == null) {
            throw new RuntimeException("The key 'Execution' is missing in config.properties!");
        }
        execution = execution.trim();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--headless=new");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        try {
            if ("remote".equalsIgnoreCase(execution)) {
                String remoteUrl = ConfigReader.getProperty("remote.url");
                if (remoteUrl == null)
                {
                    throw new RuntimeException("The key 'remote.url' is missing in config.properties for remote execution!");
                }
                tlDriver.set(new RemoteWebDriver(new URL(remoteUrl.trim()), options));
            } else {
                tlDriver.set(new ChromeDriver(options));
            }
            tlDriver.get().manage().window().maximize();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally
            {
                tlDriver.remove();
            }
        }
    }
}