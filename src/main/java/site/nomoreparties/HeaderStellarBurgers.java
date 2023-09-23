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
        driver.findElement(this.BurgerConstructor).click();
        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
    }
    public void headerFeedOrdersClick() {
        driver.findElement(this.FeedOrders).click();
        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
    }
    public void headerLogoStellarBurgerClick() {
        driver.findElement(this.LogoStellarBurger).click();
        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
    }
    public void headerPersonalAccountClick() {
        driver.findElement(this.PersonalAccount).click();
        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
    }




    /*

    личный кабинет

    //внутри конструктора:
        окно выбора ингридиентов
            текст Соберите бургер

       БургерКонструкторБаскет

     */
}
