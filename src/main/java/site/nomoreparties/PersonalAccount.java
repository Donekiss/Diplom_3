package site.nomoreparties;

import org.openqa.selenium.By;

public class PersonalAccount {
    private By ButtonLogout = By.xpath("//button[text()='Выход']");

    public By getButtonLogout() {
        return ButtonLogout;
    }
}
