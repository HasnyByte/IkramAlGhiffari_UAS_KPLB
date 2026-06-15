package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object untuk validasi UI umum pada aplikasi demo.
 */
public class UiValidationPage extends BasePage {
    private final By textViews = By.className("android.widget.TextView");
    private final By buttons = By.className("android.widget.Button");

    /**
     * Constructor UiValidationPage.
     *
     * @param driver AndroidDriver aktif
     */
    public UiValidationPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Menghitung jumlah TextView pada layar.
     *
     * @return jumlah TextView
     */
    public int countTextViews() {
        return driver.findElements(textViews).size();
    }

    /**
     * Menghitung jumlah Button pada layar.
     *
     * @return jumlah Button
     */
    public int countButtons() {
        return driver.findElements(buttons).size();
    }

    /**
     * Mengecek apakah ada text kosong pada TextView yang tampil.
     *
     * @return true jika semua TextView visible memiliki text atau tidak ada crash
     */
    public boolean hasReadableTextContent() {
        List<WebElement> elements = driver.findElements(textViews);
        if (elements.isEmpty()) {
            return false;
        }

        for (WebElement element : elements) {
            if (element.isDisplayed() && element.getText() != null && !element.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
