package tests;

import com.testaddressbook.Driver;
import org.openqa.selenium.WebDriver;

import static locators.Locators.*;

public class TestaddressbookTests {

    final static WebDriver DRIVER = Driver.getChromeDriver();
    final static String BASE_URL = "http://a.testaddressbook.com/";
    final static String SIGH_IN_URL = "sign_in";
    final static String ADDRESSES_URL = "addresses";

    final static String EMAIL = "vjx15@mail.ru";
    final static String PASSWORD = "zxc!123!ewq";

    public static void main(String[] args) {

        loginTest();
        addAddressTest();
        editAddressTest();
        deleteAddressTest();
        logoutTest();

        DRIVER.quit();
    }

    public static void loginTest() {

        DRIVER.get(BASE_URL + SIGH_IN_URL);
        DRIVER.findElement(EMAIL_FIELD).sendKeys(EMAIL);
        DRIVER.findElement(PASSWORD_FIELD).sendKeys(PASSWORD);
        DRIVER.findElement(SIGH_IN_BUTTON).click();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        String expectedURL = "http://a.testaddressbook.com/";
        String actualURL = DRIVER.getCurrentUrl();

        System.out.println("Login Test: " + actualURL.equals(expectedURL));
    }

    public static void addAddressTest() {

        DRIVER.get(BASE_URL + ADDRESSES_URL);

        DRIVER.findElement(NEW_ADDRESS_LINK).click();

        DRIVER.findElement(FIRST_NAME_FIELD).sendKeys("Jack");
        DRIVER.findElement(LAST_NAME_FIELD).sendKeys("Dawson");
        DRIVER.findElement(ADDRESS1_FIELD).sendKeys("67");
        DRIVER.findElement(ADDRESS2_FIELD).sendKeys("Highhill Street");
        DRIVER.findElement(CITY_FIELD).sendKeys("Selkirk");
        DRIVER.findElement(NEW_HAMPSHIRE_STATE_LISTBOX).click();
        DRIVER.findElement(ZIP_CODE_FIELD).sendKeys("220050");
        DRIVER.findElement(US_COUNTRY_BUTTON).click();
        DRIVER.findElement(BIRTHDAY_DATE).sendKeys("22.07.1953");
        DRIVER.findElement(COLOR_FIELD).sendKeys("#FFDD00");
        DRIVER.findElement(AGE_FIELD).sendKeys("28");
        DRIVER.findElement(WEBSITE_FIELD).sendKeys("http://www.google.com/");
        DRIVER.findElement(PICTURE_FIELD)
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");
        DRIVER.findElement(PHONE_FIELD).sendKeys("+375(29)xxx-xx-xx");
        DRIVER.findElement(DANCING_INTERESTS_CHECKBOX).click();
        DRIVER.findElement(NOTE_FIELD).sendKeys("Note note note.");

        DRIVER.findElement(ACCEPT_BUTTON).click();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully created.";
        String actualAlert = DRIVER.findElement(ALERT_NOTICE).getText();

        System.out.println("Add address Test: " + actualAlert.equals(expectedAlert));

    }

    public static void editAddressTest() {

        DRIVER.get(BASE_URL + ADDRESSES_URL);
        DRIVER.findElement(EDIT_ADDRESS_LINK).click();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        DRIVER.findElement(NEW_HAMPSHIRE_STATE_LISTBOX).click();
        DRIVER.findElement(COLOR_FIELD).sendKeys("#00FFF0");
        DRIVER.findElement(PICTURE_FIELD)
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");

        DRIVER.findElement(ACCEPT_BUTTON).click();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully updated.";
        String actualAlert = DRIVER.findElement(ALERT_NOTICE).getText();

        System.out.println("Edit address Test: " + actualAlert.equals(expectedAlert));
    }

    public static void deleteAddressTest() {

        DRIVER.get(BASE_URL + ADDRESSES_URL);
        DRIVER.findElement(DESTROY_ADDRESS_LINK).click();
        DRIVER.switchTo().alert().accept();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully destroyed.";
        String actualAlert = DRIVER.findElement(ALERT_NOTICE).getText();

        System.out.println("Delete address Test: " + actualAlert.equals(expectedAlert));
    }

    public static void logoutTest() {

        DRIVER.get(BASE_URL);
        DRIVER.findElement(SIGN_OUT_LINK).click();
        DRIVER.manage().timeouts().getPageLoadTimeout();

        String expectedURL = "http://a.testaddressbook.com/sign_in";
        String actualURL = DRIVER.getCurrentUrl();

        System.out.println("Logout Test: " + actualURL.equals(expectedURL));
    }
}
