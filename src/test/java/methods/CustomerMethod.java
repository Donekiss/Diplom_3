package methods;

import data.BaseUrl;
import data.Links;
import generator.Customer;
import generator.CustomerClient;
import generator.CustomerGenerator;
import generator.CustomerToken;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.LoginFormPage;

import java.time.Duration;

public class CustomerMethod {
    private static Customer customer;
    private final CustomerClient customerClient = new CustomerClient();
    private WebDriver driver;
    private String email;
    private String password;
    private String accessToken;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Step("Create customer through Api with setting password length")
    public void CustomerCreate(int passwordLength) {
        RestAssured.baseURI = BaseUrl.getBASE_URL();

        customer = CustomerGenerator.randomCustomer(passwordLength);
        email = customer.getEmail();
        password = customer.getPassword();
        Response response = customerClient.create(customer);
        accessToken = CustomerToken.extractAccessToken(response);
    }

    @Step("Create and login customer through Api with setting password length")
    public void CustomerCreateAndPass(WebDriver driver, int passwordLength) {
        CustomerCreate(passwordLength);

        LoginFormPage loginFormPage = new LoginFormPage();
        driver.get(Links.getPersonalAccountWindow());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginFormPage.getFieldEmail())));

        loginFormPage.setEmailInLoginForm(driver, email);
        loginFormPage.setPasswordInLoginForm(driver, password);
        loginFormPage.ButtonLoginFormClick(driver);
        wait.until(ExpectedConditions.urlToBe(Links.getConstructorWindow()));
    }

}
