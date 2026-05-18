package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
// 1. Import Log4j2 classes
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {
    // 2. Declare the logger instance for this class
    private static final Logger log = LogManager.getLogger(Hooks.class);

    public static WebDriver driver;

    @Before
    public void setup() {
        // Now 'log' will work perfectly!
        log.info("-------------------- STARTING SCENARIO SETUP --------------------");
        log.info("Reading runtime configuration properties via ConfigReader...");

        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void breakdown() {
        log.info("-------------------- TEARING DOWN SCENARIO --------------------");
        DriverFactory.quitDriver();
    }
}