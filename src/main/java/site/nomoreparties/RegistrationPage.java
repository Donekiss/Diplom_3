package site.nomoreparties;

import generator.CustomerGenerator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationPage {
    private final By NameTitleForm = By.xpath("//h2[text()='Регистрация']");
    private final By FieldName = By.xpath("//div[contains(label, 'Имя')]/input[@name='name']");
    private final By FieldEmail = By.xpath("//div[contains(label, 'Email')]/input[@type='text']");
    private final By FieldPassword = By.xpath("//div[contains(label, 'Пароль')]/input[@type='password' and @name='Пароль']");
    private final By ButtonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    private final By TextBeforeLinkToLogin = By.xpath("//p[contains(@class, 'undefined') and contains(text(), 'Уже зарегистрированы?')]");
    private final By ShortPassword = By.xpath("//p[text()='Некорректный пароль']");
    private final By LinkToLoginWindow = By.xpath("//a[@href='/login' and contains(text(), 'Войти')]");
    private RegistrationPage pageObject;
    private WebDriver driver;

    public By getLinkToLoginWindow() {
        return LinkToLoginWindow;
    }

    public void setNameInRegistrationForm(WebDriver driver, String name) {
        WebElement element = driver.findElement(FieldName);
        element.click();
        element.sendKeys(name);
    }

    public void setEmailInRegistrationForm(WebDriver driver, String email) {
        WebElement element = driver.findElement(FieldEmail);
        element.click();
        element.sendKeys(email);
    }

    public void setPasswordInRegistrationForm(WebDriver driver, String password) {
        WebElement element = driver.findElement(FieldPassword);
        element.click();
        element.clear();
        element.sendKeys(password);
    }

    @Step("Click on button registration")
    public void buttonRegistrationClick(WebDriver driver) {
        driver.findElement(ButtonRegistration).click();
    }

    @Step("Generation and record name in the registration fields")
    public String generationAndRecordName(WebDriver driver) {
        String name = CustomerGenerator.randomEmail();
        setNameInRegistrationForm(driver, name);
        return name;
    }

    @Step("Generation and record email in the registration fields")
    public String generationAndRecordEmail(WebDriver driver) {
        String email = CustomerGenerator.randomEmail();
        setEmailInRegistrationForm(driver, email);
        return email;
    }

    @Step("Generation and record password")
    public String generationAndRecordPassword(WebDriver driver, int lengthPassword) {
        String password = CustomerGenerator.randomPassword(lengthPassword);
        setPasswordInRegistrationForm(driver, password);
        return password;
    }

    @Step("Checking visibility error message on Page")
    public boolean isVisibilityError(WebDriver driver) {
        boolean isDisplayed = driver.findElement(ShortPassword).isDisplayed();
        return isDisplayed;
    }


}
