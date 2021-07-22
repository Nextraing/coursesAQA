package com.testaddressbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {

    WebDriver driver;

    public Driver() {

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "./src/main/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
    }

    public void init() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {

        return driver;
    }

    public void close() {

        this.driver.quit();
    }
}
