package tests.junit5;

import com.testaddressbook.Driver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import static com.testaddressbook.Log.LOG;
import static constants.Constants.*;
import static locators.Locators.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestaddressbookJUnit5Tests {

    private static final String CLASS_SIMPLE_NAME = TestaddressbookJUnit5Tests.class.getSimpleName();
    private final WebDriver driver = Driver.getChromeDriver();

    @BeforeAll
    void setUp() {

        LOG.info("Start of {}.", CLASS_SIMPLE_NAME);
        driver.get(BASE_URL);
    }

    @ParameterizedTest
    @CsvSource({"vjx15@mail.ru,zxc!123!ewq"})
    @Order(1)
    @Tag("Authorization")
    @DisplayName("LogIn Test")
    void loginTest(String email, String password) {

        driver.findElement(SIGN_IN_LINK).click();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SIGH_IN_BUTTON).click();

        String actualURL = driver.getCurrentUrl();

        LOG.info("  LogIn Test.");
        assertEquals(BASE_URL, actualURL, "Incorrect email or password during authorization.");
    }

    @Test
    @Disabled
    @Order(2)
    @DisplayName("Add address Test")
    void addAddressTest() {

        driver.findElement(ADDRESSES_LINK).click();
        driver.findElement(NEW_ADDRESS_LINK).click();

        driver.findElement(FIRST_NAME_FIELD).sendKeys("Jack");
        driver.findElement(LAST_NAME_FIELD).sendKeys("Dawson");
        driver.findElement(ADDRESS1_FIELD).sendKeys("67");
        driver.findElement(ADDRESS2_FIELD).sendKeys("Highhill Street");
        driver.findElement(CITY_FIELD).sendKeys("Selkirk");
        driver.findElement(NEW_HAMPSHIRE_STATE_LISTBOX).click();
        driver.findElement(ZIP_CODE_FIELD).sendKeys("220050");
        driver.findElement(US_COUNTRY_BUTTON).click();
        driver.findElement(BIRTHDAY_DATE).sendKeys("22.07.1953");
        driver.findElement(COLOR_FIELD).sendKeys("#FFDD00");
        driver.findElement(AGE_FIELD).sendKeys("28");
        driver.findElement(WEBSITE_FIELD).sendKeys("http://www.google.com/");
        driver.findElement(PICTURE_FIELD)
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");
        driver.findElement(PHONE_FIELD).sendKeys("+375(29)xxx-xx-xx");
        driver.findElement(DANCING_INTERESTS_CHECKBOX).click();
        driver.findElement(NOTE_FIELD).sendKeys("Note note note.");

        driver.findElement(ACCEPT_BUTTON).click();

        String expectedAlert = "Address was successfully created.";
        String actualAlert = driver.findElement(ALERT_NOTICE).getText();

        LOG.info("  Add address Test.");
        assertEquals(expectedAlert, actualAlert, "Adding address error.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addressData.csv", numLinesToSkip = 1)
    @Order(2)
    @DisplayName("Add address from CSV file Test")
    void addAddressFromCSVTest(ArgumentsAccessor arguments) {

        driver.findElement(ADDRESSES_LINK).click();
        driver.findElement(NEW_ADDRESS_LINK).click();

        driver.findElement(FIRST_NAME_FIELD).sendKeys(arguments.getString(0));
        driver.findElement(LAST_NAME_FIELD).sendKeys(arguments.getString(1));
        driver.findElement(ADDRESS1_FIELD).sendKeys(arguments.getString(2));
        driver.findElement(ADDRESS2_FIELD).sendKeys(arguments.getString(3));
        driver.findElement(CITY_FIELD).sendKeys(arguments.getString(4));
        driver.findElement(NEW_HAMPSHIRE_STATE_LISTBOX).click();
        driver.findElement(ZIP_CODE_FIELD).sendKeys(arguments.getString(5));
        driver.findElement(US_COUNTRY_BUTTON).click();
        driver.findElement(BIRTHDAY_DATE).sendKeys(arguments.getString(6));
        driver.findElement(COLOR_FIELD).sendKeys(arguments.getString(7));
        driver.findElement(AGE_FIELD).sendKeys(arguments.getString(8));
        driver.findElement(WEBSITE_FIELD).sendKeys(arguments.getString(9));
        driver.findElement(PICTURE_FIELD)
                .sendKeys(System.getProperty("user.dir") + arguments.getString(10));
        driver.findElement(PHONE_FIELD).sendKeys(arguments.getString(11));
        driver.findElement(DANCING_INTERESTS_CHECKBOX).click();
        driver.findElement(NOTE_FIELD).sendKeys(arguments.getString(12));

        driver.findElement(ACCEPT_BUTTON).click();

        String expectedAlert = "Address was successfully created.";
        String actualAlert = driver.findElement(ALERT_NOTICE).getText();

        LOG.info("  Add address from CSV file Test.");
        assertEquals(expectedAlert, actualAlert, "Adding address from CSV file error.");
    }

    @RepeatedTest(3)
    @Order(3)
    @DisplayName("Edit address Test")
    void editAddressTest() {

        driver.findElement(ADDRESSES_LINK).click();
        driver.findElement(EDIT_ADDRESS_LINK).click();

        driver.findElement(NEW_HAMPSHIRE_STATE_LISTBOX).click();
        driver.findElement(COLOR_FIELD).sendKeys("#00FFF0");
        driver.findElement(PICTURE_FIELD)
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");

        driver.findElement(ACCEPT_BUTTON).click();

        String expectedAlert = "Address was successfully updated.";
        String actualAlert = driver.findElement(ALERT_NOTICE).getText();

        LOG.info("  Edit address Test.");
        assertEquals(expectedAlert, actualAlert, "Editing address error.");
    }

    @Test
    @Order(4)
    @DisplayName("Delete address Test")
    void deleteAddressTest() {

        driver.findElement(ADDRESSES_LINK).click();
        driver.findElement(DESTROY_ADDRESS_LINK).click();
        driver.switchTo().alert().accept();

        String expectedAlert = "Address was successfully destroyed.";
        String actualAlert = driver.findElement(ALERT_NOTICE).getText();

        LOG.info("  Delete address Test.");
        assertEquals(expectedAlert, actualAlert, "Deleting address error.");
    }

    @Test
    @Order(5)
    @Tag("Authorization")
    @DisplayName("LogOut Test")
    void logoutTest() {

        driver.findElement(SIGN_OUT_LINK).click();

        String expectedURL = driver.findElement(SIGN_IN_LINK).getAttribute("href");
        String actualURL = driver.getCurrentUrl();

        LOG.info("  LogOut Test.");
        assertEquals(expectedURL, actualURL, "LogOut error.");
    }

    @AfterAll
    void tearDown() {

        LOG.info("End of {}.", CLASS_SIMPLE_NAME);
        driver.quit();
    }

}
