package site.nomoreparties;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private final String currentTabContainsClass = "tab_type_current";
    private final By NameTitleForm = By.xpath("//h1[text()='Соберите бургер']");
    private final By WindowAllIngredients = By.xpath("//*[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']");
    private final By BunTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Булки']/..");
    private final By SauceTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Соусы']/..");
    private final By FillingTab = By.xpath("//div[contains(@class, 'tab')]/span[text()='Начинки']/..");
    private final By ButtonPersonalAccount = By.xpath("//button[text()='Войти в аккаунт']");
    private final By BurgerIngredients = By.xpath("//a[contains(@class, 'BurgerIngredient_ingredient')]");
    private FluentWait<WebDriver> wait;

    public String getCurrentTabContainsClass() {
        return currentTabContainsClass;
    }

    public By getBunTab() {
        return BunTab;
    }

    public By getSauceTab() {
        return SauceTab;
    }

    public By getFillingTab() {
        return FillingTab;
    }

    public By getWindowAllIngredients() {
        return WindowAllIngredients;
    }

    public By getButtonPersonalAccount() {
        return ButtonPersonalAccount;
    }

    public By getBurgerIngredients() {
        return BurgerIngredients;
    }

    @Step("Waiting for all ingredients to be loaded")
    public void waitingAllIngredientsLoaded(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(9)).pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.numberOfElementsToBe(getBurgerIngredients(), 15));
    }

    public boolean isSauceTabSelected(WebDriver driver) {
        return driver.findElement(getSauceTab()).getAttribute("class").contains(getCurrentTabContainsClass());
    }

    public boolean isFillingTabSelected(WebDriver driver) {
        return driver.findElement(getFillingTab()).getAttribute("class").contains(getCurrentTabContainsClass());
    }

    public boolean isBunTabSelected(WebDriver driver) {
        return driver.findElement(getBunTab()).getAttribute("class").contains(getCurrentTabContainsClass());
    }

    @Step("Switching to the sauce tab")
    public void switchingToSauceTab(WebDriver driver) {
        driver.findElement(SauceTab).click();
    }

    @Step("Switching to the filling tab")
    public void switchingToFillingTab(WebDriver driver) {
        driver.findElement(FillingTab).click();
    }

    @Step("Switching to the bun tab")
    public void switchingToBunTab(WebDriver driver) {
        driver.findElement(BunTab).click();
    }

}
