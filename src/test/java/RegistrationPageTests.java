import data.Links;
import generator.CustomerClient;
import generator.CustomerGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.RegistrationPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;

public class RegistrationPageTests {

    private WebDriver driver;
    private int passwordLength = 8;
    private int passwordBelowBoundaryValue = 5;
    private RegistrationPage pageObject;
    private String expected;
    private String token;
    private String email;
    private String name;
    private String password;

    @Before
    public void startUp() {
        driver = createWebDriver();
        pageObject = new RegistrationPage();
        driver.get(Links.getRegistrationWindow());
        generationNameAndEmail();
        recordNameAndEmail();
    }
    @Test
    @DisplayName("Registration ")
    @Description("Check registration with long password length")
    public void checkRegistration() {
        generationAndRecordPassword(passwordLength);
        buttonRegistrationClick();
        waitingChangeUrl();
        equivalenceURL();
    }
    @Test
    @DisplayName("Registration with used short password")
    @Description("Check visibility error massage with used short password length")
    public void checkRegistrationShortLogin() {
        generationAndRecordPassword(passwordBelowBoundaryValue);
        buttonRegistrationClick();
        checkingVisibilityError();
    }
    @Step("Generation name and email")
    public void generationNameAndEmail(){
        email = CustomerGenerator.randomEmail();
        name = CustomerGenerator.randomEmail();
    }
    @Step("Record name and email in the registration fields")
    public void recordNameAndEmail(){
        pageObject.setEmailInRegistrationForm(driver, email);
        pageObject.setNameInRegistrationForm(driver, name);
    }
    @Step("Generation and record password")
    public void generationAndRecordPassword(int lengthPassword){
        password = CustomerGenerator.randomPassword(lengthPassword);
        pageObject.setPasswordInRegistrationForm(driver, password);
    }
    @Step("Click on button registration")
    public void buttonRegistrationClick(){
        pageObject.ButtonRegistrationClick(driver);
    }
    @Step("Checking visibility error on Page")
    public void checkingVisibilityError(){
        WebElement errorShortPassword = driver.findElement(pageObject.getShortPassword());
        boolean isShortPasswordVisible = errorShortPassword.isDisplayed();
        Assert.assertEquals("Элемент с сообщением об ошибке использования короткого пароля", true, isShortPasswordVisible);
    }
    @Step("Waiting for change URL")
    public void waitingChangeUrl(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until((WebDriver d) -> d.getCurrentUrl().equals(Links.getPersonalAccountWindow()));
    }
    @Step("Equivalence comparison URL")
    public void equivalenceURL(){
        expected = driver.getCurrentUrl().toString();
        Assert.assertEquals("Не открылась страница - " + Links.getPersonalAccountWindow(), expected, Links.getPersonalAccountWindow());
    }
    @After
    public void teardown() {
        token = CustomerClient.loginCustomer(email, password);
        if (token != null){
            CustomerClient.deleteCustomer(token);
        }
        this.driver.quit();
    }
}
