import data.Links;
import generator.CustomerClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import methods.CustomerMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.*;

import java.util.ArrayList;
import java.util.Collection;

import static data.Links.getPersonalAccountWindow;
import static driver.WebDriverCreator.createWebDriver;

@RunWith(Parameterized.class)
public class SwitchToLoginFormTests {
    private final String expectedProfileUrl = getPersonalAccountWindow();
    private final String ErrorMassage = "Переход на страницу Входа в аккаунт не произведен";
    private final String link;
    private final By element;
    private final CustomerMethod customerMethod = new CustomerMethod();
    private WebDriver driver;
    private LoginFormPage pageObject;
    private String email;
    private String password;

    public SwitchToLoginFormTests(String link, By element) {
        this.link = link;
        this.element = element;
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

    @Before
    public void startUp() {
        customerMethod.CustomerCreate(8);
        password = customerMethod.getPassword();
        email = customerMethod.getEmail();
        driver = createWebDriver();
    }

    @Test
    @DisplayName("Check transfer to the Login page")
    @Description("Switching to the Login page from 4 places")
    public void SwitchToLoginFormTest() {
        driver.get(link);
        driver.findElement(element).click();
        pageObject = new LoginFormPage();
        pageObject.waitVisibilityLoginFormPage(driver);
        Assert.assertEquals(ErrorMassage, expectedProfileUrl, driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
