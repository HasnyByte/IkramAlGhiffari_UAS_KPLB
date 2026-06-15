package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UiValidationPage;
import utils.ExtentReportManager;

/**
 * Test class untuk validasi tampilan UI aplikasi.
 */
public class UiValidationTest extends BaseTest {

    @Test(description = "TC007 - UI validation: halaman memiliki text readable dan button/element interaktif")
    public void TC007_uiContentValidation() {
        UiValidationPage uiValidationPage = new UiValidationPage(getDriver());

        int textViewCount = uiValidationPage.countTextViews();
        int buttonCount = uiValidationPage.countButtons();

        ExtentReportManager.logInfo("Jumlah TextView: " + textViewCount);
        ExtentReportManager.logInfo("Jumlah Button: " + buttonCount);

        Assert.assertTrue(textViewCount > 0, "Tidak ada TextView yang terdeteksi pada layar.");
        Assert.assertTrue(uiValidationPage.hasReadableTextContent(), "Tidak ada konten text yang readable pada layar.");

        ExtentReportManager.logPass("Validasi UI berhasil. TextView: " + textViewCount + ", Button: " + buttonCount);
    }
}
