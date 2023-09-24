package site.nomoreparties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private WebDriver driver;
    private By NameTitleForm = By.xpath("//h2[text()='Регистрация']");
    private By FieldName = By.xpath("//div[contains(label, 'Имя')]/input[@name='name']");
    private By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private By FieldPassword = By.xpath("//div[contains(label, 'Пароль')]/input[@type='password' and @name='Пароль']");
    private By ButtonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    private By TextBeforeLinkToLogin = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Уже зарегистрированы?')]");

    private By LinkToLoginWindow = By.xpath("//a[@href='/login' and contains(text(), 'Войти')]");
    public By getLinkToLoginWindow() {
        return LinkToLoginWindow;
    }

    /*public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }*/
    public void setNameInRegistrationForm(String name) {
        WebElement element = driver.findElement(FieldName);
        element.click();
        element.sendKeys(name);
    }
    public void setEmailInRegistrationForm(String email) {
        WebElement element = driver.findElement(FieldEmail);
        element.click();
        element.sendKeys(email);
    }
    public void setPasswordInRegistrationForm(String password) {
        WebElement element = driver.findElement(FieldPassword);
        element.click();
        element.sendKeys(password);
    }
    public void ButtonRegistrationClick() {
        driver.findElement(ButtonRegistration).click();
    }
    public void LinkToLoginWindowClick() {
        driver.findElement(LinkToLoginWindow).click();
    }
    public WebElement registrationPageLinkToLoginAccount() {
        return driver.findElement(LinkToLoginWindow);
    }

}
