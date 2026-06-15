package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object untuk halaman utama Skill2Lead AndroidDemoApp.
 */
public class MainPage extends BasePage {
    private final By allElements = By.xpath("//*");
    private final By textViews = By.className("android.widget.TextView");
    private final By buttons = By.className("android.widget.Button");
    private final By clickableElements = AppiumBy.androidUIAutomator("new UiSelector().clickable(true)");

    /**
     * Constructor MainPage.
     *
     * @param driver AndroidDriver aktif
     */
    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Mengecek apakah package aplikasi yang aktif sudah benar.
     *
     * @param expectedPackage package yang diharapkan
     * @return true jika package aktif sesuai
     */
    public boolean isCorrectPackageOpened(String expectedPackage) {
        return expectedPackage.equals(driver.getCurrentPackage());
    }

    /**
     * Mengambil activity Android yang sedang aktif.
     *
     * @return nama activity aktif
     */
    public String getCurrentActivity() {
        return driver.currentActivity();
    }

    /**
     * Mengambil semua element pada layar.
     *
     * @return list element pada layar
     */
    public List<WebElement> getAllElements() {
        return findElements(allElements);
    }

    /**
     * Mengecek apakah halaman memiliki element UI yang tampil.
     *
     * @return true jika jumlah element lebih dari nol
     */
    public boolean hasVisibleElements() {
        return !getAllElements().isEmpty();
    }

    /**
     * Mengambil semua TextView yang tampil pada layar.
     *
     * @return list TextView
     */
    public List<WebElement> getTextViews() {
        return findElements(textViews);
    }

    /**
     * Mengambil text dari semua TextView yang tidak kosong.
     *
     * @return list string text visible
     */
    public List<String> getVisibleTexts() {
        return getTextViews()
                .stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Mengambil semua button yang terdeteksi.
     *
     * @return list button
     */
    public List<WebElement> getButtons() {
        return findElements(buttons);
    }

    /**
     * Mengecek apakah terdapat minimal satu element clickable.
     *
     * @return true jika ada element clickable
     */
    public boolean hasClickableElement() {
        return !findElements(clickableElements).isEmpty();
    }

    /**
     * Klik element clickable pertama pada halaman.
     */
    public void tapFirstClickableElement() {
        List<WebElement> elements = findElements(clickableElements);
        if (elements.isEmpty()) {
            throw new RuntimeException("Tidak ada element clickable pada halaman.");
        }
        elements.get(0).click();
    }

    /**
     * Mengambil page source aplikasi untuk validasi sederhana.
     *
     * @return page source XML
     */
    public String getPageSource() {
        return driver.getPageSource();
    }
}
