package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordRecoveryPage {
    private WebDriver driver;
    private By NameTitleForm = By.xpath("//h2[text()='Восстановление пароля']");
    private By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private By ButtonRecovery = By.xpath("//button[text()='Восстановить']");
    private By TextBeforeLinkToLogin = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Вспомнили пароль?')]");

    public By getLinkToLoginWindow() {
        return LinkToLoginWindow;
    }
    private By LinkToLoginWindow = By.xpath("//a[@href='/login' and contains(text(), 'Войти')]");



}
