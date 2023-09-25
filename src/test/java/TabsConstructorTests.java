import data.Links;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.ConstructorPage;

import java.time.Duration;

public class TabsConstructorTests {
    private WebDriver driver;
    private ConstructorPage pageObject;
    private JavascriptExecutor js;
    private String expectedContains = "tab_type_current";
    private String ErrorMassageTab = "Выбранная вкладка не стала текущей";
    private String ErrorMassageScroll = "Перемещение скролла на заданную величину";
    private WebDriverWait wait;
    private WebElement fillingTab;
    private WebElement sauceTab;
    private WebElement bunTab;
    private WebElement scrollWindow;
    private int countIngredients = 15;
    private long expectedFillingScrollTop = 940;
    private long expectedSauceScrollTop = 351;
    private long expectedBunScrollTop = 0;
    private String valueClassAfterClick;

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        pageObject = new ConstructorPage();
        js = (JavascriptExecutor) driver;
        driver.get(Links.getConstructorWindow());
        waitingAllIngredientsLoaded(driver, countIngredients);
        initializingWebElements(driver);
    }

    @Test
    @DisplayName("Transition to the fillings tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void FillingTabClick() {
        fillingTab.click();
        waitingForAnAttributeChange(fillingTab);

        checkScrollPosition(scrollWindow, expectedFillingScrollTop);
        ComparisonElementAttributes(fillingTab);
    }

    @Test
    @DisplayName("Transition to the sauce tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void SauceTabClick() {
        sauceTab.click();
        waitingForAnAttributeChange(sauceTab);

        checkScrollPosition(scrollWindow, expectedSauceScrollTop);
        ComparisonElementAttributes(sauceTab);
    }

    @Test
    @DisplayName("Transition to the bun tab")
    @Description("Checking tab attribute changes and scroll movement")
    public void BunTabClick() {
        sauceTab.click();
        checkScrollPosition(scrollWindow, expectedSauceScrollTop);
        bunTab.click();
        waitingForAnAttributeChange(bunTab);

        checkScrollPosition(scrollWindow, expectedBunScrollTop);
        ComparisonElementAttributes(bunTab);
    }

    @Step("Waiting for all ingredients to be loaded")
    public void waitingAllIngredientsLoaded(WebDriver driver, int ingredients) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.numberOfElementsToBe(pageObject.getBurgerIngredients(), ingredients));
    }

    @Step("Initializing tabs of found web elements")
    public void initializingWebElements(WebDriver driver) {
        scrollWindow = driver.findElement(pageObject.getWindowAllIngredients());
        fillingTab = driver.findElement(pageObject.getFillingTab());
        sauceTab = driver.findElement(pageObject.getSauceTab());
        bunTab = driver.findElement(pageObject.getBunTab());
    }

    @Step("waiting for an attribute change")
    public void waitingForAnAttributeChange(WebElement attribute) {
        wait.until(ExpectedConditions.attributeContains(attribute, "class", expectedContains));
    }

    @Step("Comparison changes to the tab attributes")
    public void ComparisonElementAttributes(WebElement tab) {
        valueClassAfterClick = tab.getAttribute("class");
        Assert.assertTrue(ErrorMassageTab, valueClassAfterClick.contains(expectedContains));
    }

    @Step("Checking the scroll position change")
    public void checkScrollPosition(WebElement scroll, long expected) {
        wait.until(driver -> {
            long actualScrollTop = (long) js.executeScript("return arguments[0].scrollTop;", scroll);
            return actualScrollTop >= expected;
        });
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
