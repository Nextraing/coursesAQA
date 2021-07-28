package com.testaddressbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {

    public static void init() {

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "./src/main/resources/chromedriver.exe");
    }

    public static WebDriver getChromeDriver() {

        init();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }
}
