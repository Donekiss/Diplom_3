import data.Links;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.ConstructorPage;

import java.time.Duration;


public class TabsConstructorTests {
    private final String ErrorMassageTab = "Выбранная вкладка не стала текущей";
    private WebDriver driver;
    private ConstructorPage pageObject;
    private WebElement scrollWindow;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        pageObject = new ConstructorPage();
        js = (JavascriptExecutor) driver;
        driver.get(Links.getConstructorWindow());
        pageObject.waitingAllIngredientsLoaded(driver);
        scrollWindow = driver.findElement(pageObject.getWindowAllIngredients());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    @DisplayName("Transition to the fillings tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void FillingTabClick() {
        pageObject.switchingToFillingTab(driver);
        wait.until(driver -> {
            long actualScrollTop = (long) js.executeScript("return arguments[0].scrollTop;", scrollWindow);
            return actualScrollTop >= 940;
        });
        boolean actual = pageObject.isFillingTabSelected(driver);
        Assert.assertTrue(ErrorMassageTab, actual);
    }

    @Test
    @DisplayName("Transition to the sauce tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void SauceTabClick() {
        pageObject.switchingToSauceTab(driver);
        wait.until(driver -> {
            long actualScrollTop = (long) js.executeScript("return arguments[0].scrollTop;", scrollWindow);
            return actualScrollTop >= 351;
        });
        boolean actual = pageObject.isSauceTabSelected(driver);
        Assert.assertTrue(ErrorMassageTab, actual);
    }

    @Test
    @DisplayName("Transition to the bun tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void BunTabClick() {
        pageObject.switchingToFillingTab(driver);
        wait.until(driver -> {
            long actualScrollTop = (long) js.executeScript("return arguments[0].scrollTop;", scrollWindow);
            return actualScrollTop >= 940;
        });
        pageObject.switchingToBunTab(driver);
        wait.until(driver -> {
            long actualScrollTop = (long) js.executeScript("return arguments[0].scrollTop;", scrollWindow);
            return actualScrollTop <= 50;
        });
        boolean actual = pageObject.isBunTabSelected(driver);
        Assert.assertTrue(ErrorMassageTab, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
