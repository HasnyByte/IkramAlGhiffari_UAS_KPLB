package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.ConfigReader;
import utils.ExtentReportManager;

/**
 * Test class untuk validasi launch aplikasi.
 */
public class LaunchTest extends BaseTest {

    @Test(description = "TC001 - Positive case: aplikasi berhasil dibuka dan package sesuai")
    public void TC001_appLaunchesSuccessfully() {
        MainPage mainPage = new MainPage(getDriver());
        String expectedPackage = ConfigReader.getValue("appPackage");

        ExtentReportManager.logInfo("Mengecek package aplikasi aktif.");
        Assert.assertTrue(
                mainPage.isCorrectPackageOpened(expectedPackage),
                "Package aplikasi tidak sesuai. Expected: " + expectedPackage + ", Actual: " + getDriver().getCurrentPackage()
        );
        ExtentReportManager.logPass("Aplikasi berhasil dibuka dengan package: " + expectedPackage);
    }

    @Test(description = "TC002 - Validasi activity utama aplikasi berhasil terbuka")
    public void TC002_mainActivityIsDisplayed() {
        MainPage mainPage = new MainPage(getDriver());
        String expectedActivity = ConfigReader.getValue("appActivity");

        ExtentReportManager.logInfo("Mengecek activity aplikasi aktif.");
        Assert.assertTrue(
                expectedActivity.contains(mainPage.getCurrentActivity().replace(".", ""))
                        || mainPage.getCurrentActivity().contains("MainActivity"),
                "Activity utama tidak sesuai. Actual: " + mainPage.getCurrentActivity()
        );
        ExtentReportManager.logPass("Activity utama aplikasi berhasil tampil: " + mainPage.getCurrentActivity());
    }
}
