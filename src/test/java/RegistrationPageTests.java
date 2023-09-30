import data.Links;
import generator.CustomerClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.RegistrationPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;

public class RegistrationPageTests {
    private final int passwordLength = 8;
    private final int passwordBelowBoundaryValue = 4;
    private WebDriver driver;
    private RegistrationPage pageObject;
    private String password;
    private String name;
    private String email;
    private String token;

    @Before
    public void startUp() {
        driver = createWebDriver();
        pageObject = new RegistrationPage();
        driver.get(Links.getRegistrationWindow());
        name = pageObject.generationAndRecordName(driver);
        email = pageObject.generationAndRecordEmail(driver);
    }

    @Test
    @DisplayName("Registration ")
    @Description("Check registration with long password length")
    public void checkRegistration() {
        password = pageObject.generationAndRecordPassword(driver, passwordLength);
        pageObject.buttonRegistrationClick(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(Links.getPersonalAccountWindow()));
        Assert.assertEquals("Регистрация не прошла", Links.getPersonalAccountWindow(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration with used short password")
    @Description("Check visibility error massage with used short password length")
    public void checkRegistrationShortLogin() {
        password = pageObject.generationAndRecordPassword(driver, passwordBelowBoundaryValue);
        pageObject.buttonRegistrationClick(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        boolean isActual = pageObject.isVisibilityError(driver);
        Assert.assertTrue("Элемент с сообщением об ошибке использования короткого пароля", isActual);
    }

    @After
    public void teardown() {
        token = CustomerClient.loginCustomer(email, password);
        if (token != null) {
            CustomerClient.deleteCustomer(token);
        }
        this.driver.quit();
    }
}
