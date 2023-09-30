import data.Links;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.HeaderStellarBurgers;

import static driver.WebDriverCreator.createWebDriver;

public class HeaderStellarBurgersTests {
    private final String ErrorMassage = "Не открылась страница";
    private WebDriver driver;
    private HeaderStellarBurgers pageObject;
    private String expected;

    @Before
    public void startUp() {
        driver = createWebDriver();
        pageObject = new HeaderStellarBurgers();
        driver.get(Links.getRegistrationWindow());
    }

    @Test
    @DisplayName("Check transition through Constructor button")
    @Description("Checking transition used button Constructor")
    public void headerBurgerConstructorClick() {
        pageObject.headerBurgerConstructorClick(driver);

        expected = driver.getCurrentUrl();
        Assert.assertEquals(ErrorMassage, expected, Links.getConstructorWindow());
    }

    @Test
    @DisplayName("Check transition through logo")
    @Description("Checking transition used logo")
    public void logoStellarBurgerClick() {
        pageObject.clickOnLogo(driver);

        expected = driver.getCurrentUrl();
        Assert.assertEquals(ErrorMassage, expected, Links.getConstructorWindow());
    }

    @Test
    @DisplayName("Check transition through Feed orders")
    @Description("Checking transition used button Feed orders")
    public void feedOrdersClick() {
        pageObject.headerFeedOrdersClick(driver);

        expected = driver.getCurrentUrl();
        Assert.assertEquals(ErrorMassage, expected, Links.getFeedOrdersWindow());
    }

    @Test
    @DisplayName("Check transition through Personal account")
    @Description("Checking transition used button Personal account")
    public void personalAccountClick() {
        pageObject.headerPersonalAccountClick(driver);

        expected = driver.getCurrentUrl();
        Assert.assertEquals(ErrorMassage, expected, Links.getPersonalAccountWindow());
    }

    @After
    public void teardown() {
        this.driver.quit();
    }

}
