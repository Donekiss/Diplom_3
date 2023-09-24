package site.nomoreparties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HeaderStellarBurgers {
    private WebDriver driver;
    private By BurgerConstructor = By.xpath("//p[text()='Конструктор']");
    private By FeedOrders = By.xpath("//p[text()='Лента Заказов']");
    private By LogoStellarBurger = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private By PersonalAccount = By.xpath("//a[@class='AppHeader_header__link__3D_hX']/p[contains(text(),'Личный Кабинет')]");

    public By getPersonalAccount() {
        return PersonalAccount;
    }
    public  WebElement headerPersonalAccount() {
        return driver.findElement(this.PersonalAccount);
    }

    public void headerBurgerConstructorClick(WebDriver driver) {
        driver.findElement(this.BurgerConstructor).click();
    }
    public void headerFeedOrdersClick(WebDriver driver) {
        driver.findElement(this.FeedOrders).click();
    }
    public void headerLogoStellarBurgerClick(WebDriver driver) {
        driver.findElement(this.LogoStellarBurger).click();
    }
    public void headerPersonalAccountClick(WebDriver driver) {
        driver.findElement(this.PersonalAccount).click();
    }
    public By getBurgerConstructor() {
        return BurgerConstructor;
    }

    public By getLogoStellarBurger() {
        return LogoStellarBurger;
    }
}
