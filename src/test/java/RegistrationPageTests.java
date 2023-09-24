import data.Links;
import generator.CustomerClient;
import generator.CustomerGenerator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.RegistrationPage;

import java.time.Duration;

public class RegistrationPageTests {

    private WebDriver driver;
    private RegistrationPage pageObject;
    private CustomerClient customerClient = new CustomerClient();
    private String expected;
    private String token;
    private String email;
    private String password;
    private String name;
    @Before
    public void startUp() {
        driver = new ChromeDriver();
        pageObject = new RegistrationPage();
        driver.get(Links.getRegistrationWindow());
        email = CustomerGenerator.randomEmail();
        name = CustomerGenerator.randomName();
        password = CustomerGenerator.randomPassword(8);
        pageObject.setNameInRegistrationForm(name);
        pageObject.setPasswordInRegistrationForm(password);
        pageObject.setEmailInRegistrationForm(email);
    }
    @Test
    public void checkRegistration() {
        pageObject.ButtonRegistrationClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(Links.getPersonalAccountWindow()));
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals("Не открылась страница - " + Links.getPersonalAccountWindow(), expected, Links.getPersonalAccountWindow());

        token = CustomerClient.loginCustomer(email, password);
        Assert.assertNotNull("Регистрация не прошла, accessToken равен null", token);
        CustomerClient.deleteCustomer(token); ;
    }
    @Test
    public void checkRegistrationShortLogin() {
        password = password.substring(0, 6);
        pageObject.ButtonRegistrationClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(Links.getPersonalAccountWindow()));
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals("Не открылась страница - " + Links.getPersonalAccountWindow(), expected, Links.getPersonalAccountWindow());

        token = CustomerClient.loginCustomer(email, password);
        Assert.assertNotNull("Регистрация не прошла, accessToken равен null", token);
        CustomerClient.deleteCustomer(token);
    }
    @After
    public void teardown() {
        this.driver.quit();
    }
}
