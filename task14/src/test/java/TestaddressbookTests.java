import com.testaddressbook.Driver;
import org.openqa.selenium.By;

public class TestaddressbookTests {

    final static String URL = "http://a.testaddressbook.com/";
    final static String EMAIL = "vjx15@mail.ru";
    final static String PASSWORD = "zxc!123!ewq";

    public static void main(String[] args) {

        loginTest();
        addAddressTest();
        editAddressTest();
        deleteAddressTest();
        logoutTest();
    }

    public static void loginTest() {

        Driver driver = new Driver();

        login(driver);

        String expectedURL = "http://a.testaddressbook.com/";
        String actualURL = driver.getDriver().getCurrentUrl();

        System.out.println("Login Test: " + actualURL.equals(expectedURL));

        driver.close();
    }

    public static void addAddressTest() {

        Driver driver = new Driver();

        login(driver);

        driver.getDriver().navigate().to(URL + "addresses");
        driver.getDriver().findElement(By.linkText("New Address")).click();

        driver.getDriver().findElement(By.cssSelector("#address_first_name"))
                .sendKeys("Jack");
        driver.getDriver().findElement(By.xpath(".//input[@id='address_last_name']"))
                .sendKeys("Dawson");
        driver.getDriver().findElement(By.name("address[address1]"))
                .sendKeys("67");
        driver.getDriver().findElement(By.cssSelector("input[name='address[address2]']"))
                .sendKeys("Highhill Street");
        driver.getDriver().findElement(By.id("address_city"))
                .sendKeys("Selkirk");
        driver.getDriver().findElement(By.xpath(".//select[@id='address_state']/option[@value='NH']")).click();
        driver.getDriver().findElement(By.name("address[zip_code]"))
                .sendKeys("220050");
        driver.getDriver().findElement(By.name("address[country]")).click();
        driver.getDriver().findElement(By.cssSelector("input[type='date']"))
                .sendKeys("22.07.1953");
        driver.getDriver().findElement(By.cssSelector("input[type='color']"))
                .sendKeys("#FFDD00");
        driver.getDriver().findElement(By.name("address[age]"))
                .sendKeys("28");
        driver.getDriver().findElement(By.id("address_website"))
                .sendKeys("http://www.google.com/");
        driver.getDriver().findElement(By.id("address_picture"))
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");
        driver.getDriver().findElement(By.name("address[phone]"))
                .sendKeys("+375(29)xxx-xx-xx");
        driver.getDriver().findElement(By.id("address_interest_dance")).click();
        driver.getDriver().findElement(By.name("address[note]"))
                .sendKeys("Note note note.");

        driver.getDriver().findElement(By.cssSelector(".btn-primary")).click();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully created.";
        String actualAlert = driver.getDriver()
                .findElement(By.xpath(".//div[@class='alert alert-notice']")).getText();

        System.out.println("Add address Test: " + actualAlert.equals(expectedAlert));

        driver.close();
    }

    public static void editAddressTest() {

        Driver driver = new Driver();

        login(driver);

        driver.getDriver().navigate().to(URL + "addresses");
        driver.getDriver().findElement(By.linkText("Edit")).click();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();

        driver.getDriver().findElement(By.xpath(".//select[@id='address_state']/option[@value='NH']")).click();
        driver.getDriver().findElement(By.cssSelector("input[type='color']"))
                .sendKeys("#00FFF0");
        driver.getDriver().findElement(By.id("address_picture"))
                .sendKeys(System.getProperty("user.dir") + "./src/main/resources/pikachu.png");

        driver.getDriver().findElement(By.cssSelector(".btn-primary")).click();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully updated.";
        String actualAlert = driver.getDriver()
                .findElement(By.xpath(".//div[@class='alert alert-notice']")).getText();

        System.out.println("Edit address Test: " + actualAlert.equals(expectedAlert));

        driver.getDriver().close();
    }

    public static void deleteAddressTest() {

        Driver driver = new Driver();

        login(driver);

        driver.getDriver().navigate().to(URL + "addresses");
        driver.getDriver().findElement(By.linkText("Destroy")).click();
        driver.getDriver().switchTo().alert().accept();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();

        String expectedAlert = "Address was successfully destroyed.";
        String actualAlert = driver.getDriver()
                .findElement(By.xpath(".//div[@class='alert alert-notice']")).getText();

        System.out.println("Delete address Test: " + actualAlert.equals(expectedAlert));

        driver.close();
    }

    public static void logoutTest() {

        Driver driver = new Driver();

        login(driver);

        driver.getDriver().findElement(By.linkText("Sign out")).click();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();

        String expectedURL = "http://a.testaddressbook.com/sign_in";
        String actualURL = driver.getDriver().getCurrentUrl();

        System.out.println("Logout Test: " + actualURL.equals(expectedURL));

        driver.close();
    }

    public static void login(Driver driver) {

        driver.init();
        driver.getDriver().get(URL + "sign_in");
        driver.getDriver().findElement(By.id("session_email")).sendKeys(EMAIL);
        driver.getDriver().findElement(By.id("session_password")).sendKeys(PASSWORD);
        driver.getDriver().findElement(By.name("commit")).click();
        driver.getDriver().manage().timeouts().getPageLoadTimeout();
    }

}
