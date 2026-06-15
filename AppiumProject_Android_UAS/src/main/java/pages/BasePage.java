package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

/**
 * Base page untuk reusable action pada Page Object Model.
 */
public class BasePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    /**
     * Constructor BasePage.
     *
     * @param driver AndroidDriver aktif
     */
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        int explicitWait = ConfigReader.getIntValue("explicitWait");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    /**
     * Menunggu element tampil.
     *
     * @param locator locator element
     * @return WebElement yang tampil
     */
    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Menunggu element dapat diklik.
     *
     * @param locator locator element
     * @return WebElement yang clickable
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Klik element berdasarkan locator.
     *
     * @param locator locator element
     */
    public void click(By locator) {
        waitForClickable(locator).click();
    }

    /**
     * Mengisi text pada element input.
     *
     * @param locator locator input
     * @param text text yang akan diketik
     */
    public void type(By locator, String text) {
        waitForVisible(locator).sendKeys(text);
    }

    /**
     * Clear input lalu isi text baru.
     *
     * @param locator locator input
     * @param text text yang akan diketik
     */
    public void clearAndType(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Mengambil text dari element.
     *
     * @param locator locator element
     * @return text element
     */
    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    /**
     * Mengecek apakah element tampil.
     *
     * @param locator locator element
     * @return true jika tampil, false jika tidak
     */
    public boolean isDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Mencari banyak element menggunakan locator.
     *
     * @param locator locator element
     * @return list WebElement
     */
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
}
