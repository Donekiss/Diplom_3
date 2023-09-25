import customer.CustomerMethod;
import data.Links;
import generator.CustomerClient;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.HeaderStellarBurgers;
import site.nomoreparties.PersonalAccount;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;

public class SurfProfilePageTests {
    private WebDriver driver;
    private String profileWindow = Links.getProfileWindow();
    private String constructorWindow = Links.getConstructorWindow();
    private CustomerMethod customerMethod = new CustomerMethod();
    private HeaderStellarBurgers pageObject;
    private String ErrorMassage = "Переход залогиненого клиента, на эту страницу не прошел - ";
    @Before
    public void setUp() {
        driver = createWebDriver();
        customerMethod.CustomerCreateAndPass(driver,8);
        pageObject = new HeaderStellarBurgers();
    }
    @Test
    @DisplayName("Check transition to Profile page")
    @Description("Checking transition to Profile by click Personal Account button")
    public void SwitchToProfileTest() {
        clickOnPersonalAccountButton();
        waitingForEntryAndChangeUrl(driver, profileWindow);

        Assert.assertEquals(ErrorMassage + profileWindow, profileWindow, driver.getCurrentUrl().toString());
    }
    @Test
    @DisplayName("Check transition to Constructor page")
    @Description("Checking transition to Constructor page by click Constructor button")
    public void SwitchFromProfileToConstructorPageTest() {
        clickOnPersonalAccountButton();
        waitingForEntryAndChangeUrl(driver, profileWindow);

        driver.findElement(pageObject.getBurgerConstructor()).click();

        Assert.assertEquals(ErrorMassage + constructorWindow, constructorWindow, driver.getCurrentUrl().toString());
    }
    @Test
    @DisplayName("Check transition to Constructor page used logo")
    @Description("Checking transition to Constructor page by click Logo")
    public void SwitchFromProfileToConstructorPageWithLogoTest() {
        clickOnPersonalAccountButton();
        waitingForEntryAndChangeUrl(driver, profileWindow);

        driver.findElement(pageObject.getLogoStellarBurger()).click();

        Assert.assertEquals(ErrorMassage + constructorWindow, constructorWindow, driver.getCurrentUrl().toString());
    }
    @Test
    @DisplayName("Check account logout")
    @Description("Checking account logout used Exit button")
    public void SwitchOffProfileTest() {
        clickOnPersonalAccountButton();
        waitingForEntryAndChangeUrl(driver, profileWindow);

        PersonalAccount personalAccount = new PersonalAccount();
        driver.findElement(personalAccount.getButtonLogout()).click();

        Assert.assertEquals(ErrorMassage + profileWindow, profileWindow, driver.getCurrentUrl().toString());
    }
    @Step("Go to Profile used Personal Account button")
    public void clickOnPersonalAccountButton(){
        driver.findElement(pageObject.getPersonalAccount()).click();
    }
    @Step("Waiting change URL and login to account")
    public void waitingForEntryAndChangeUrl(WebDriver driver, String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(url));
    }
    @After
    public void tearDown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
