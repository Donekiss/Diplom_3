package site.nomoreparties;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccount {
    private final By ButtonLogout = By.xpath("//button[text()='Выход']");

    public By getButtonLogout() {
        return ButtonLogout;
    }

    @Step("logout from personal account")
    public void logoutFromPersonalAccount(WebDriver driver) {
        driver.findElement(getButtonLogout()).click();
    }
}
