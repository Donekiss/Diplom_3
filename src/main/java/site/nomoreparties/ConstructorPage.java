package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage {
    private WebDriver driver;
    private By NameTitleForm = By.xpath("//h1[text()='Соберите бургер']");

    private By ButtonPersonalAccount = By.xpath("//button[text()='Войти в аккаунт']");
    public By getButtonPersonalAccount() {
        return ButtonPersonalAccount;
    }
    public WebElement constructorPersonalAccount() {
        return driver.findElement(ButtonPersonalAccount);
    }
}
