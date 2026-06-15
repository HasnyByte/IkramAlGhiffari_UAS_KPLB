package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Manager untuk membuat report HTML menggunakan ExtentReports.
 */
public final class ExtentReportManager {
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentReportManager() {
        // Utility class tidak perlu dibuat object.
    }

    /**
     * Inisialisasi ExtentReports dan folder output.
     */
    public static void initReport() {
        String reportPath = ConfigReader.getValue("reportPath", "test-output/ExtentReport.html");
        File reportFile = new File(reportPath);
        File parentDir = reportFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("UAS Appium Android Automation Report");
        sparkReporter.config().setReportName("Skill2Lead AndroidDemoApp Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester", "Ikram Al Ghiffari");
        extentReports.setSystemInfo("Platform", ConfigReader.getValue("platformName", "Android"));
        extentReports.setSystemInfo("Automation", ConfigReader.getValue("automationName", "UiAutomator2"));
        extentReports.setSystemInfo("Device", ConfigReader.getValue("deviceName", "emulator-5554"));
        extentReports.setSystemInfo("Android Version", ConfigReader.getValue("platformVersion", "15"));
    }

    /**
     * Membuat node test baru pada report.
     *
     * @param testName nama test case
     * @param description deskripsi test case
     */
    public static void createTest(String testName, String description) {
        if (extentReports == null) {
            initReport();
        }
        ExtentTest test = extentReports.createTest(testName, description);
        extentTest.set(test);
    }

    /**
     * Log informasi ke report.
     *
     * @param message pesan info
     */
    public static void logInfo(String message) {
        getTest().info(message);
    }

    /**
     * Log status PASS ke report.
     *
     * @param message pesan pass
     */
    public static void logPass(String message) {
        getTest().pass(message);
    }

    /**
     * Log status FAIL ke report.
     *
     * @param message pesan fail
     */
    public static void logFail(String message) {
        getTest().fail(message);
    }

    /**
     * Log status FAIL lengkap dengan screenshot.
     *
     * @param message pesan fail
     * @param driver AndroidDriver aktif
     */
    public static void logFailWithScreenshot(String message, AndroidDriver driver) {
        try {
            String screenshotPath = takeScreenshot(driver);
            getTest().fail(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            getTest().fail(message + " | Screenshot gagal dibuat: " + e.getMessage());
        }
    }

    /**
     * Mengambil screenshot dari kondisi layar saat ini.
     *
     * @param driver AndroidDriver aktif
     * @return path screenshot
     */
    public static String takeScreenshot(AndroidDriver driver) {
        String screenshotDirectory = ConfigReader.getValue("screenshotPath", "test-output/screenshots/");
        File directory = new File(screenshotDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String screenshotPath = screenshotDirectory + "screenshot_" + timestamp + ".png";
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotPath);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new RuntimeException("Gagal menyimpan screenshot: " + e.getMessage(), e);
        }

        return screenshotPath;
    }

    /**
     * Menulis semua report ke file HTML.
     */
    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
        extentTest.remove();
    }

    /**
     * Mengambil ExtentTest aktif dari ThreadLocal.
     *
     * @return ExtentTest aktif
     */
    private static ExtentTest getTest() {
        ExtentTest test = extentTest.get();
        if (test == null) {
            throw new IllegalStateException("ExtentTest belum dibuat. Panggil createTest() terlebih dahulu.");
        }
        return test;
    }
}
