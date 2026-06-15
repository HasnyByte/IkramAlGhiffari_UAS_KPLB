package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentReportManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Base class untuk semua test. Class ini bertanggung jawab membuat Appium session,
 * menyediakan driver untuk test class, serta menutup driver setelah semua test selesai.
 */
public class BaseTest {
    protected static AndroidDriver driver;

    /**
     * Setup Appium AndroidDriver sebelum suite berjalan.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        ExtentReportManager.initReport();

        if (driver == null) {
            driver = createAndroidDriver();
            int implicitWait = ConfigReader.getIntValue("implicitWait");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        }
    }

    /**
     * Membuat log report sebelum setiap test method.
     *
     * @param result informasi test method dari TestNG
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentReportManager.createTest(methodName, description == null ? "Automation test case" : description);
    }

    /**
     * Menulis status test ke ExtentReports setelah setiap test method.
     *
     * @param result hasil eksekusi test dari TestNG
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.logPass("Test berhasil dijalankan.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.logFailWithScreenshot(
                    "Test gagal: " + result.getThrowable().getMessage(),
                    driver
            );
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.logInfo("Test dilewati: " + result.getName());
        }
    }

    /**
     * Menutup driver dan menyimpan report setelah semua test selesai.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        ExtentReportManager.flushReport();
    }

    /**
     * Method untuk mengakses AndroidDriver dari semua test class.
     *
     * @return AndroidDriver aktif
     */
    public static AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver belum dibuat. Pastikan setUpSuite() berjalan terlebih dahulu.");
        }
        return driver;
    }

    /**
     * Membuat AndroidDriver menggunakan UiAutomator2Options sesuai config.properties.
     *
     * @return AndroidDriver baru
     */
    private AndroidDriver createAndroidDriver() {
        String appiumServerUrl = ConfigReader.getValue("appiumServerUrl");
        String appPath = ConfigReader.getValue("appPath");
        File appFile = new File(appPath);

        if (!appFile.exists()) {
            throw new RuntimeException(
                    "APK tidak ditemukan di: " + appFile.getAbsolutePath() + "\n" +
                    "Copy dulu Android_Appium_Demo.apk ke folder apps/."
            );
        }

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(ConfigReader.getValue("platformName"))
                .setAutomationName(ConfigReader.getValue("automationName"))
                .setDeviceName(ConfigReader.getValue("deviceName"))
                .setPlatformVersion(ConfigReader.getValue("platformVersion"))
                .setApp(appFile.getAbsolutePath())
                .setAppPackage(ConfigReader.getValue("appPackage"))
                .setAppActivity(ConfigReader.getValue("appActivity"))
                .setNoReset(ConfigReader.getBooleanValue("noReset"))
                .setFullReset(ConfigReader.getBooleanValue("fullReset"));

        try {
            AndroidDriver androidDriver = new AndroidDriver(new URL(appiumServerUrl), options);
            SessionId sessionId = androidDriver.getSessionId();
            System.out.println("Appium session berhasil dibuat: " + sessionId);
            return androidDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL Appium Server tidak valid: " + appiumServerUrl, e);
        }
    }
}
