package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.ExtentReportManager;

import java.util.List;

/**
 * Test class untuk validasi halaman utama/home aplikasi demo.
 */
public class HomeTest extends BaseTest {

    @Test(description = "TC003 - Validasi halaman utama menampilkan elemen UI")
    public void TC003_homePageShowsVisibleElements() {
        MainPage mainPage = new MainPage(getDriver());

        ExtentReportManager.logInfo("Mengecek apakah halaman utama memiliki elemen UI.");
        Assert.assertTrue(mainPage.hasVisibleElements(), "Halaman utama tidak memiliki elemen UI yang terdeteksi.");

        List<String> visibleTexts = mainPage.getVisibleTexts();
        ExtentReportManager.logInfo("Text yang terdeteksi pada halaman: " + visibleTexts);
        Assert.assertFalse(visibleTexts.isEmpty(), "Tidak ada text yang terlihat pada halaman utama.");

        ExtentReportManager.logPass("Halaman utama berhasil menampilkan elemen UI dan text.");
    }

    @Test(description = "TC004 - Validasi halaman utama memiliki elemen yang dapat diklik")
    public void TC004_homePageHasClickableElement() {
        MainPage mainPage = new MainPage(getDriver());

        ExtentReportManager.logInfo("Mengecek element clickable pada halaman utama.");
        Assert.assertTrue(mainPage.hasClickableElement(), "Tidak ada element clickable pada halaman utama.");

        ExtentReportManager.logPass("Halaman utama memiliki minimal satu element clickable.");
    }
}
