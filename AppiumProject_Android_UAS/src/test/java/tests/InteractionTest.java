package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.NavigationPage;
import utils.ConfigReader;
import utils.ExtentReportManager;

/**
 * Test class untuk interaksi dasar pada aplikasi.
 */
public class InteractionTest extends BaseTest {

    @Test(description = "TC005 - Validasi klik elemen pertama tidak menyebabkan aplikasi crash")
    public void TC005_tapFirstClickableElementDoesNotCrash() {
        MainPage mainPage = new MainPage(getDriver());
        String expectedPackage = ConfigReader.getValue("appPackage");

        ExtentReportManager.logInfo("Melakukan tap pada element clickable pertama.");
        mainPage.tapFirstClickableElement();

        ExtentReportManager.logInfo("Memvalidasi aplikasi tetap berjalan pada package yang benar.");
        Assert.assertEquals(getDriver().getCurrentPackage(), expectedPackage, "Aplikasi keluar dari package setelah element diklik.");

        ExtentReportManager.logPass("Tap element berhasil dan aplikasi tidak crash.");
    }

    @Test(description = "TC006 - Edge case: tombol Back lalu aplikasi dapat diaktifkan kembali")
    public void TC006_backNavigationAndReactivateApp() {
        NavigationPage navigationPage = new NavigationPage(getDriver());
        String expectedPackage = ConfigReader.getValue("appPackage");

        ExtentReportManager.logInfo("Menekan tombol Back Android.");
        navigationPage.pressBack();

        ExtentReportManager.logInfo("Mengaktifkan ulang aplikasi jika keluar dari layar utama.");
        navigationPage.activateApp(expectedPackage);

        Assert.assertTrue(navigationPage.isAppStillOpened(expectedPackage), "Aplikasi tidak berhasil diaktifkan kembali.");
        Assert.assertTrue(navigationPage.hasScreenContent(), "Tidak ada konten pada layar setelah aplikasi diaktifkan kembali.");

        ExtentReportManager.logPass("Aplikasi tetap stabil setelah tombol Back dan re-activate.");
    }
}
