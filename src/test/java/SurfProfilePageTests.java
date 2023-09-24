import customer.CustomerMethod;
import data.Links;
import generator.CustomerClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.HeaderStellarBurgers;

import java.time.Duration;

public class SurfProfilePageTests {
    private WebDriver driver;
    private CustomerMethod customerMethod = new CustomerMethod();
    private HeaderStellarBurgers headerStellarBurgers = new HeaderStellarBurgers();
    private String ErrorMassage = "Переход залогиненого клиента, на эту страницу не прошел - ";
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        customerMethod.CustomerCreateAndPass(driver,8);
    }

    @Test
    public void SwitchToProfileTest() {
        driver.findElement(headerStellarBurgers.getPersonalAccount()).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(Links.getProfileWindow()));

        Assert.assertEquals(ErrorMassage + Links.getProfileWindow(), Links.getProfileWindow(), driver.getCurrentUrl().toString());
    }
    @Test
    public void SwitchFromProfileToConstructorPageTest() {
        driver.findElement(headerStellarBurgers.getPersonalAccount()).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(Links.getProfileWindow()));
        driver.findElement(headerStellarBurgers.getBurgerConstructor()).click();

        Assert.assertEquals(ErrorMassage + Links.getConstructorWindow(), Links.getConstructorWindow(), driver.getCurrentUrl().toString());
    }
    @Test
    public void SwitchFromProfileToConstructorPageWithLogoTest() {
        driver.findElement(headerStellarBurgers.getPersonalAccount()).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(Links.getProfileWindow()));
        driver.findElement(headerStellarBurgers.getLogoStellarBurger()).click();

        Assert.assertEquals(ErrorMassage + Links.getConstructorWindow(), Links.getConstructorWindow(), driver.getCurrentUrl().toString());
    }
    @Test
    public void SwitchOffProfileTest() {
        driver.findElement(headerStellarBurgers.getPersonalAccount()).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(Links.getProfileWindow()));

        Assert.assertEquals(ErrorMassage + Links.getProfileWindow(), Links.getProfileWindow(), driver.getCurrentUrl().toString());
    }
    @After
    public void tearDown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
