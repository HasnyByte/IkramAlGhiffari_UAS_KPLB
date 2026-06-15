package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object untuk aksi navigasi umum pada aplikasi Android.
 */
public class NavigationPage extends BasePage {
    /**
     * Constructor NavigationPage.
     *
     * @param driver AndroidDriver aktif
     */
    public NavigationPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Menekan tombol back Android.
     */
    public void pressBack() {
        driver.navigate().back();
    }

    /**
     * Mengaktifkan ulang aplikasi berdasarkan app package.
     *
     * @param appPackage package aplikasi
     */
    public void activateApp(String appPackage) {
        driver.activateApp(appPackage);
    }

    /**
     * Mengecek apakah aplikasi masih berada pada package yang benar.
     *
     * @param expectedPackage package yang diharapkan
     * @return true jika package aktif sesuai
     */
    public boolean isAppStillOpened(String expectedPackage) {
        try {
            return expectedPackage.equals(driver.getCurrentPackage());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Mengecek apakah ada element pada layar setelah navigasi.
     *
     * @return true jika layar masih memiliki element
     */
    public boolean hasScreenContent() {
        return !driver.findElements(By.xpath("//*")).isEmpty();
    }
}
