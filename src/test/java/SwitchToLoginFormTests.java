import customer.CustomerMethod;
import data.Links;

import generator.CustomerClient;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static data.Links.getPersonalAccountWindow;
import static driver.WebDriverCreator.createWebDriver;

@RunWith(Parameterized.class)
public class SwitchToLoginFormTests {
    private WebDriver driver;
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
        driver = createWebDriver();
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
    @DisplayName("Check transfer to the Login page")
    @Description("Switching to the Login page from 4 places")
    public void SwitchToLoginFormTest() {
        getToLink(link);
        clickOnFoundElement(element);
        waitVisibility();
        equivalenceURL();
    }
    @Step("Going to the initial page")
    public void getToLink(String link){
        driver.get(link);
    }
    @Step("Click on found element")
    public void clickOnFoundElement(By element){
        driver.findElement(element).click();
    }
    @Step("Waiting for the element visibility")
    public void waitVisibility(){
        LoginFormPage loginFormPage = new LoginFormPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginFormPage.getFieldEmail())));
    }
    @Step("Equivalence comparison URL")
    public void equivalenceURL(){
        Assert.assertEquals(ErrorMassage, expectedProfileUrl, driver.getCurrentUrl().toString());
    }

    @After
    public void teardown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
