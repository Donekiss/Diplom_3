package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFormPage {
    private static WebDriver driver;
    private By NameTitleForm = By.xpath("//h2[text()='Вход']");

    public By getFieldEmail() {
        return FieldEmail;
    }

    private static By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private static By FieldPassword = By.xpath("//div[contains(label, 'Пароль')]/input[@type='password' and @name='Пароль']");
    private static By ButtonLogin = By.xpath("//button[text()='Войти']");
    private By TextBeforeLinkToRegistration = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'новый пользователь?')]");
    private By LinkToRegistrationWindow = By.xpath("//a[@href='/register' and contains(text(), 'Зарегистрироваться')]");
    private By TextBeforeLinkToPasswordRecovery = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Забыли пароль?')]");
    private By LinkToPasswordRecovery = By.xpath("//a[@href='/forgot-password' and contains(text(), 'Восстановить пароль')]");

    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void setEmailInLoginForm(String email) {
        WebElement element = driver.findElement(FieldEmail);
        element.click();
        element.sendKeys(email);
    }
    public static void setPasswordInLoginForm(String password) {
        WebElement element = driver.findElement(FieldPassword);
        element.click();
        element.sendKeys(password);
    }
    public static void ButtonLoginFormClick() {
        driver.findElement(ButtonLogin).click();
    }
    public void LinkToRegistrationWindowClick() {
        driver.findElement(LinkToRegistrationWindow).click();
    }
    public void LinkToPasswordRecoveryClick() {
        driver.findElement(LinkToPasswordRecovery).click();
    }
}
