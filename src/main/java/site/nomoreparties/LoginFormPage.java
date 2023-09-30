package site.nomoreparties;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormPage {
    private final By NameTitleForm = By.xpath("//h2[text()='Вход']");
    private final By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private final By FieldPassword = By.xpath("//div[contains(label, 'Пароль')]/input[@type='password' and @name='Пароль']");
    private final By ButtonLogin = By.xpath("//button[text()='Войти']");
    private final By TextBeforeLinkToRegistration = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'новый пользователь?')]");
    private final By LinkToRegistrationWindow = By.xpath("//a[@href='/register' and contains(text(), 'Зарегистрироваться')]");
    private final By TextBeforeLinkToPasswordRecovery = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Забыли пароль?')]");
    private final By LinkToPasswordRecovery = By.xpath("//a[@href='/forgot-password' and contains(text(), 'Восстановить пароль')]");
    private WebDriver driver;

    public By getFieldEmail() {
        return FieldEmail;
    }

    public By getButtonLogin() {
        return ButtonLogin;
    }

    public By getFieldPassword() {
        return FieldPassword;
    }

    public void setEmailInLoginForm(WebDriver driver, String email) {
        WebElement element = driver.findElement(FieldEmail);
        element.sendKeys(email);
    }

    public void setPasswordInLoginForm(WebDriver driver, String password) {
        WebElement element = driver.findElement(FieldPassword);
        element.sendKeys(password);
    }

    public void ButtonLoginFormClick(WebDriver driver) {
        WebElement element = driver.findElement(ButtonLogin);
        element.click();
    }

    @Step("Waiting for the element visibility")
    public void waitVisibilityLoginFormPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(getFieldEmail())));
    }


}
