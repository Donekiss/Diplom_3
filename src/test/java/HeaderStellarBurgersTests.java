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
    String expected;
    String actual;
    private String ConstructorWindow = "https://stellarburgers.nomoreparties.site/";
    private String FeedOrdersWindow = "https://stellarburgers.nomoreparties.site/feed";
    private String PersonalAccountWindow = "https://stellarburgers.nomoreparties.site/login";
    private String RegistrationWindow = "https://stellarburgers.nomoreparties.site/register";
    private String ErrorMassage = "Не открылась страница - ";

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        pageObject = new HeaderStellarBurgers(this.driver);
        this.driver.get(RegistrationWindow);
    }
    @Test
        public void checkHeaderBurgerConstructorClick() {
        pageObject.headerBurgerConstructorClick();
        expected = this.driver.getCurrentUrl().toString();
        actual = ConstructorWindow;
        Assert.assertEquals(ErrorMassage + ConstructorWindow, expected, actual);
    }
    @Test
    public void checkLogoStellarBurgerClick() {
        pageObject.headerLogoStellarBurgerClick();
        expected = this.driver.getCurrentUrl().toString();
        actual = ConstructorWindow;
        Assert.assertEquals(ErrorMassage + ConstructorWindow, expected, actual);
    }
    @Test
    public void checkFeedOrdersClick() {
        pageObject.headerFeedOrdersClick();
        expected = this.driver.getCurrentUrl().toString();
        actual = FeedOrdersWindow;
        Assert.assertEquals(ErrorMassage + FeedOrdersWindow, expected, actual);
    }
    @Test
    public void checkPersonalAccountClick() {
        pageObject.headerPersonalAccountClick();
        expected = this.driver.getCurrentUrl().toString();
        actual = PersonalAccountWindow;
        Assert.assertEquals(ErrorMassage + PersonalAccountWindow, expected, actual);
    }
    @After
    public void teardown() {
        this.driver.quit();
    }

}
