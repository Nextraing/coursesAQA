package locators;

import org.openqa.selenium.By;

public class Locators {

    public static final By EMAIL_FIELD = By.id("session_email");
    public static final By PASSWORD_FIELD = By.id("session_password");
    public static final By SIGH_IN_BUTTON = By.name("commit");
    public static final By SIGN_OUT_LINK = By.linkText("Sign out");

    public static final By NEW_ADDRESS_LINK = By.linkText("New Address");
    public static final By EDIT_LINK = By.linkText("Edit");
    public static final By DESTROY_LINK = By.linkText("Destroy");

    public static final By FIRST_NAME_FIELD = By.cssSelector("#address_first_name");
    public static final By LAST_NAME_FIELD = By.xpath(".//input[@id='address_last_name']");
    public static final By ADDRESS1_FIELD = By.name("address[address1]");
    public static final By ADDRESS2_FIELD = By.cssSelector("input[name='address[address2]']");
    public static final By CITY_FIELD = By.id("address_city");
    public static final By NEW_HAMPSHIRE_STATE_LISTBOX = By.xpath(".//select[@id='address_state']/option[@value='NH']");
    public static final By ZIP_CODE_FIELD = By.name("address[zip_code]");
    public static final By US_COUNTRY_BUTTON = By.id("address_country_us");
    public static final By BIRTHDAY_DATE = By.cssSelector("input[type='date']");
    public static final By COLOR_FIELD = By.cssSelector("input[type='color']");
    public static final By AGE_FIELD = By.name("address[age]");
    public static final By WEBSITE_FIELD = By.id("address_website");
    public static final By PICTURE_FIELD = By.id("address_picture");
    public static final By PHONE_FIELD = By.name("address[phone]");
    public static final By DANCING_INTERESTS_CHECKBOX = By.id("address_interest_dance");
    public static final By NOTE_FIELD = By.name("address[note]");

    public static final By ACCEPT_BUTTON = By.cssSelector(".btn-primary");

    public static final By ALERT_NOTICE = By.xpath(".//div[@class='alert alert-notice']");

}
