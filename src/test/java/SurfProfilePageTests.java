import data.Links;
import generator.CustomerClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import methods.CustomerMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.HeaderStellarBurgers;
import site.nomoreparties.PersonalAccount;

import static driver.WebDriverCreator.createWebDriver;

public class SurfProfilePageTests {
    private final String profileWindow = Links.getProfileWindow();
    private final String constructorWindow = Links.getConstructorWindow();
    private final CustomerMethod customerMethod = new CustomerMethod();
    private final String ErrorMassage = "Переход залогиненого клиента, на страницу не прошел";
    private WebDriver driver;
    private HeaderStellarBurgers pageObject;

    @Before
    public void setUp() {
        driver = createWebDriver();
        customerMethod.CustomerCreateAndPass(driver, 8);
        pageObject = new HeaderStellarBurgers();
    }

    @Test
    @DisplayName("Check transition to Profile page")
    @Description("Checking transition to Profile by click Personal Account button")
    public void SwitchToProfileTest() {
        pageObject.clickOnPersonalAccountButton(driver, Links.getProfileWindow());

        Assert.assertEquals(ErrorMassage, Links.getProfileWindow(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check transition to Constructor page")
    @Description("Checking transition to Constructor page by click Constructor button")
    public void SwitchFromProfileToConstructorPageTest() {
        pageObject.clickOnPersonalAccountButton(driver, Links.getProfileWindow());
        pageObject.clickOnConstructor(driver);

        Assert.assertEquals(ErrorMassage, Links.getConstructorWindow(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check transition to Constructor page used logo")
    @Description("Checking transition to Constructor page by click Logo")
    public void SwitchFromProfileToConstructorPageWithLogoTest() {
        pageObject.clickOnPersonalAccountButton(driver, Links.getProfileWindow());
        pageObject.clickOnLogo(driver);

        Assert.assertEquals(ErrorMassage, Links.getConstructorWindow(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check account logout")
    @Description("Checking account logout used Exit button")
    public void SwitchOffProfileTest() {
        pageObject.clickOnPersonalAccountButton(driver, Links.getProfileWindow());
        PersonalAccount personalAccount = new PersonalAccount();
        personalAccount.logoutFromPersonalAccount(driver);

        Assert.assertEquals(ErrorMassage, Links.getProfileWindow(), driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
        CustomerClient.deleteCustomer(customerMethod.getAccessToken());
    }
}
