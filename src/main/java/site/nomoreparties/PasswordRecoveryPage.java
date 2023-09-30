package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final By NameTitleForm = By.xpath("//h2[text()='Восстановление пароля']");
    private final By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private final By ButtonRecovery = By.xpath("//button[text()='Восстановить']");
    private final By TextBeforeLinkToLogin = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Вспомнили пароль?')]");
    private final By LinkToLoginWindow = By.xpath("//a[@href='/login' and contains(text(), 'Войти')]");
    private WebDriver driver;

    public By getLinkToLoginWindow() {
        return LinkToLoginWindow;
    }


}
