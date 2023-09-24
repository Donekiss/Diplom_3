import data.BaseUrl;
import data.Links;

import generator.Customer;
import generator.CustomerClient;
import generator.CustomerGenerator;
import generator.CustomerToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static data.Links.getProfileWindow;

@RunWith(Parameterized.class)
public class SwitchToLoginFormTests {
    private static WebDriver driver;
    private static HeaderStellarBurgers pageObject;
    private HeaderStellarBurgers headerStellarBurgers = new HeaderStellarBurgers();
    private String email;
    private String password;
    private String expectedProfileUrl = getProfileWindow();
    private String ErrorMassage = "Вход в аккаунт не прошел";
    private String accessToken;
    private String link;
    private By element;
    private CustomerClient customerClient = new CustomerClient();
    private static Customer customer;

    public SwitchToLoginFormTests(String link, By element) {
        this.link = link;
        this.element = element;
    }

    @Before
    public void startUp() {
        RestAssured.baseURI = BaseUrl.getBASE_URL();

        customer = CustomerGenerator.randomCustomer(8);
        email = customer.getEmail();
        password = customer.getPassword();
        Response response = customerClient.create(customer);
        accessToken = CustomerToken.extractAccessToken(response);

        driver = new ChromeDriver();
        pageObject = new HeaderStellarBurgers();

    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();

        HeaderStellarBurgers headerStellarBurgers = new HeaderStellarBurgers();
        By elementHeader = headerStellarBurgers.getPersonalAccount();

        ConstructorPage constructorPage = new ConstructorPage();
        By elementConstructor = constructorPage.getButtonPersonalAccount();

        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage();
        By elementRecovery = passwordRecoveryPage.getLinkToLoginWindow();

        RegistrationPage registrationPage = new RegistrationPage();
        By elementRegistration = registrationPage.getLinkToLoginWindow();

        data.add(new Object[]{Links.getConstructorWindow(), elementHeader});
        data.add(new Object[]{Links.getConstructorWindow(), elementConstructor});
        data.add(new Object[]{Links.getPasswordRecoveryWindow(), elementRecovery});
        data.add(new Object[]{Links.getRegistrationWindow(), elementRegistration});

        return data;
    }
    @Test
    public void SwitchToLoginFormTest() {
        driver.get(link);
        driver.findElement(element).click();

        LoginFormPage loginFormPage = new LoginFormPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginFormPage.getFieldEmail())));

        loginFormPage.setEmailInLoginForm(email);
        loginFormPage.setPasswordInLoginForm(password);
        loginFormPage.ButtonLoginFormClick();

        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

        driver.findElement(By.xpath("//button[text()='Оформить заказ']"));
        List<WebElement> elements = driver.findElements(By.xpath("//button[text()='Оформить заказ']"));
        Assert.assertFalse("Кнопка оформления заказа не найдена на странице", elements.isEmpty());

        driver.findElement(headerStellarBurgers.getPersonalAccount()).click();
        wait.until(ExpectedConditions.urlToBe(expectedProfileUrl));

        Assert.assertEquals(ErrorMassage, expectedProfileUrl, driver.getCurrentUrl().toString());

        WebElement element1 = driver.findElement(By.xpath("//button[text()='Выход']")); //Оформить заказ

        element1.click();
    }
    @After
    public void teardown() {
        driver.quit();
        CustomerClient.deleteCustomer(accessToken);
    }

}
