package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage {
    private WebDriver driver;
    private By NameTitleForm = By.xpath("//h1[text()='Соберите бургер']");
    private By WindowAllIngredients = By.xpath("//*[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']");
    private By BunTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Булки']/..");
    private By SauceTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Соусы']/..");
    private By FillingTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Начинки']/..");
    private By ButtonPersonalAccount = By.xpath("//button[text()='Войти в аккаунт']");
    private By BurgerIngredients = By.xpath("//a[contains(@class, 'BurgerIngredient_ingredient')]");
    private By NameBlockBunIngredients = By.xpath("//h2[text()='Булки']");
    private By NameBlockSauceIngredients = By.xpath("//h2[text()='Соусы']");
    private By NameBlockFillingIngredients = By.xpath("//h2[text()='Начинки']");

    public By getBunTab() {
        return BunTab;
    }
    public By getSauceTab() {
        return SauceTab;
    }
    public By getFillingTab() {
        return FillingTab;
    }
    public By getWindowAllIngredients() {
        return WindowAllIngredients;
    }
    public By getButtonPersonalAccount() {
        return ButtonPersonalAccount;
    }
    public WebElement constructorPersonalAccount() {
        return driver.findElement(ButtonPersonalAccount);
    }
    public By getBurgerIngredients() {
        return BurgerIngredients;
    }

}
