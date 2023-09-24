import customer.CustomerMethod;
import data.Links;

import generator.CustomerClient;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static data.Links.getPersonalAccountWindow;

@RunWith(Parameterized.class)
public class SwitchToLoginFormTests {
    private WebDriver driver;
    private HeaderStellarBurgers headerStellarBurgers = new HeaderStellarBurgers();
    private String email;
    private String password;
    private String expectedProfileUrl = getPersonalAccountWindow();
    private String ErrorMassage = "Переход на страницу Входа в аккаунт не произведен";
    private String link;
    private By element;
    private CustomerMethod customerMethod = new CustomerMethod();

    public SwitchToLoginFormTests(String link, By element) {
        this.link = link;
        this.element = element;
    }
    @Before
    public void startUp() {
        customerMethod.CustomerCreate(8);
        password = customerMethod.getPassword();
        email = customerMethod.getEmail();

        driver = new ChromeDriver();
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

        LoginFormPage loginFormPage = new LoginFormPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginFormPage.getFieldEmail())));
        Assert.assertEquals(ErrorMassage, expectedProfileUrl, driver.getCurrentUrl().toString());
    }
    @After
    public void teardown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
