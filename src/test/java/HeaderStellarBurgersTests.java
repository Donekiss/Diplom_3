import data.Links;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.HeaderStellarBurgers;

public class HeaderStellarBurgersTests {
    private WebDriver driver;
    private HeaderStellarBurgers pageObject;
    private String expected;
    private String ErrorMassage = "Не открылась страница - ";

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        pageObject = new HeaderStellarBurgers();
        driver.get(Links.getRegistrationWindow());
    }
    @Test
        public void headerBurgerConstructorClick() {
        pageObject.headerBurgerConstructorClick(driver);
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals(ErrorMassage + Links.getConstructorWindow(), expected, Links.getConstructorWindow());
    }
    @Test
    public void logoStellarBurgerClick() {
        pageObject.headerLogoStellarBurgerClick(driver);
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals(ErrorMassage + Links.getConstructorWindow(), expected, Links.getConstructorWindow());
    }
    @Test
    public void feedOrdersClick() {
        pageObject.headerFeedOrdersClick(driver);
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals(ErrorMassage + Links.getFeedOrdersWindow(), expected, Links.getFeedOrdersWindow());
    }
    @Test
    public void personalAccountClick() {
        pageObject.headerPersonalAccountClick(driver);
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals(ErrorMassage + Links.getPersonalAccountWindow(), expected, Links.getPersonalAccountWindow());
    }
    @After
    public void teardown() {
        this.driver.quit();
    }

}
