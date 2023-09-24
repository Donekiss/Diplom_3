package site.nomoreparties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFormPage {
    private WebDriver driver;
    private By NameTitleForm = By.xpath("//h2[text()='Вход']");

    public By getFieldEmail() {
        return FieldEmail;
    }

    public By getButtonLogin() {
        return ButtonLogin;
    }
    private By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    public By getFieldPassword() {
        return FieldPassword;
    }
    private By FieldPassword = By.xpath("//div[contains(label, 'Пароль')]/input[@type='password' and @name='Пароль']");
    private By ButtonLogin = By.xpath("//button[text()='Войти']");
    private By TextBeforeLinkToRegistration = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'новый пользователь?')]");
    private By LinkToRegistrationWindow = By.xpath("//a[@href='/register' and contains(text(), 'Зарегистрироваться')]");
    private By TextBeforeLinkToPasswordRecovery = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Забыли пароль?')]");
    private By LinkToPasswordRecovery = By.xpath("//a[@href='/forgot-password' and contains(text(), 'Восстановить пароль')]");
    public void setEmailInLoginForm(WebDriver driver, String email){
        WebElement element = driver.findElement(FieldEmail);
        element.sendKeys(email);
    }
    public void setPasswordInLoginForm(WebDriver driver, String password){
        WebElement element = driver.findElement(FieldPassword);
        element.sendKeys(password);
    }
    public void ButtonLoginFormClick(WebDriver driver){
        WebElement element = driver.findElement(ButtonLogin);
        element.click();
    }


}
