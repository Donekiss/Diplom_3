import data.Links;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.ConstructorPage;

import java.time.Duration;

public class TabsConstructorTests {
    private WebDriver driver;
    private ConstructorPage constructorPage = new ConstructorPage();
    private String expectedContains = "tab_type_current";
    private String ErrorMassage = "Выбранная вкладка не стала текущей";
    private WebDriverWait wait;
    private WebElement fillingTab;
    private WebElement sauceTab;
    private WebElement bunTab;
    private int countIngredients = 15;
    private String valueClassAfterClick;

    @Before
    public void startUp() {
        driver = new ChromeDriver();
        driver.get(Links.getConstructorWindow());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.numberOfElementsToBe(constructorPage.getBurgerIngredients(), countIngredients));
        fillingTab = driver.findElement(constructorPage.getFillingTab());
        sauceTab = driver.findElement(constructorPage.getSauceTab());
        bunTab = driver.findElement(constructorPage.getBunTab());
    }
    @Test
    public void FillingTabClick() {
        fillingTab.click();
        wait.until(ExpectedConditions.attributeContains(fillingTab, "class", expectedContains));

        valueClassAfterClick = fillingTab.getAttribute("class");
        Assert.assertTrue(ErrorMassage, valueClassAfterClick.contains(expectedContains));
    }
    @Test
    public void SauceTabClick() {
        sauceTab.click();
        wait.until(ExpectedConditions.attributeContains(sauceTab, "class", expectedContains));

        valueClassAfterClick = sauceTab.getAttribute("class");
        Assert.assertTrue(ErrorMassage, valueClassAfterClick.contains(expectedContains));
    }
    @Test
    public void BunTabClick() {
        sauceTab.click();
        bunTab.click();
        wait.until(ExpectedConditions.attributeContains(bunTab, "class", expectedContains));

        valueClassAfterClick = bunTab.getAttribute("class");
        Assert.assertTrue(ErrorMassage, valueClassAfterClick.contains(expectedContains));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
