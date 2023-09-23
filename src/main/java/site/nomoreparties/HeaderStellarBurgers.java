package site.nomoreparties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;


public class HeaderStellarBurgers {
    private WebDriver driver;
    private By BurgerConstructor = By.xpath("//p[text()='Конструктор']");
    private By FeedOrders = By.xpath("//p[text()='Лента Заказов']");
    private By LogoStellarBurger = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private By PersonalAccount = By.xpath("//p[text()='Личный Кабинет']");

    public HeaderStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    public void headerBurgerConstructorClick() {
        this.driver.findElement(this.BurgerConstructor).click();
        this.driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
    }
    public void headerFeedOrdersClick() {
        this.driver.findElement(this.FeedOrders).click();
        this.driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
    }
    public void headerLogoStellarBurgerClick() {
        this.driver.findElement(this.LogoStellarBurger).click();
        this.driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
    }
    public void headerPersonalAccountClick() {
        this.driver.findElement(this.PersonalAccount).click();
        this.driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
    }




    /*

    личный кабинет

    //внутри конструктора:
        окно выбора ингридиентов
            текст Соберите бургер

       БургерКонструкторБаскет

     */
}
