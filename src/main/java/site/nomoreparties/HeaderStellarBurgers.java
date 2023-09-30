package site.nomoreparties;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HeaderStellarBurgers {
    private final By BurgerConstructor = By.xpath("//p[text()='Конструктор']");
    private final By FeedOrders = By.xpath("//p[text()='Лента Заказов']");
    private final By LogoStellarBurger = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By PersonalAccount = By.xpath("//a[@class='AppHeader_header__link__3D_hX']/p[contains(text(),'Личный Кабинет')]");
    private WebDriver driver;

    public By getPersonalAccount() {
        return PersonalAccount;
    }

    public WebElement headerPersonalAccount() {
        return driver.findElement(PersonalAccount);
    }

    @Step("Click on burger Constructor")
    public void headerBurgerConstructorClick(WebDriver driver) {
        driver.findElement(BurgerConstructor).click();
    }

    @Step("Click on Feed Orders")
    public void headerFeedOrdersClick(WebDriver driver) {
        driver.findElement(FeedOrders).click();
    }

    @Step("Click on Personal Account")
    public void headerPersonalAccountClick(WebDriver driver) {
        driver.findElement(PersonalAccount).click();
    }

    public By getBurgerConstructor() {
        return BurgerConstructor;
    }

    public By getLogoStellarBurger() {
        return LogoStellarBurger;
    }

    @Step("Click on logo")
    public void clickOnLogo(WebDriver driver) {
        driver.findElement(getLogoStellarBurger()).click();
    }

    @Step("Click on Constructor")
    public void clickOnConstructor(WebDriver driver) {
        driver.findElement(getBurgerConstructor()).click();
    }

    @Step("Go to Profile used Personal Account button")
    public void clickOnPersonalAccountButton(WebDriver driver, String window) {
        driver.findElement(getPersonalAccount()).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(window));
    }
}
