# UAS Appium Android Automation Testing

![Java](https://img.shields.io/badge/Java-11%2F17-blue)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-orange)
![Appium](https://img.shields.io/badge/Appium-Java%20Client-green)
![TestNG](https://img.shields.io/badge/TestNG-Test%20Framework-red)
![ExtentReports](https://img.shields.io/badge/ExtentReports-HTML%20Report-purple)
![Android](https://img.shields.io/badge/Android-15-brightgreen)

Project ini dibuat untuk memenuhi UAS mata kuliah **Kualitas Perangkat Lunak**. Project berisi automation testing untuk aplikasi mobile Android menggunakan **Appium**, **Java**, **Maven**, **TestNG**, **Page Object Model**, dan **ExtentReports**.

---

## Daftar Isi

- [Informasi Project](#informasi-project)
- [Aplikasi yang Diuji](#aplikasi-yang-diuji)
- [Struktur Folder](#struktur-folder)
- [Prerequisites](#prerequisites)
- [Instalasi dan Konfigurasi Appium](#instalasi-dan-konfigurasi-appium)
- [Setup Emulator Android](#setup-emulator-android)
- [Konfigurasi Project](#konfigurasi-project)
- [Cara Menjalankan Test](#cara-menjalankan-test)
- [Test Case](#test-case)
- [Penjelasan Script](#penjelasan-script)
- [Report dan Screenshot](#report-dan-screenshot)
- [Troubleshooting](#troubleshooting)
- [Checklist Submit UAS](#checklist-submit-uas)

---

## Informasi Project

| Item | Keterangan |
|---|---|
| IDE | IntelliJ IDEA Community Edition |
| Bahasa | Java |
| Build Tool | Maven |
| Framework Automation | Appium |
| Appium Driver | UiAutomator2 |
| Test Framework | TestNG |
| Design Pattern | Page Object Model |
| Reporting | ExtentReports |
| Platform | Android |
| Device | emulator-5554 |
| Android Version | 15 |

---

## Aplikasi yang Diuji

Aplikasi yang digunakan adalah **Skill2Lead AndroidDemoApp**.

| Item | Keterangan |
|---|---|
| Nama APK | Android_Appium_Demo.apk |
| Package | com.skill2lead.appiumdemo |
| Activity | com.skill2lead.appiumdemo.MainActivity |

Aplikasi ini dipilih karena memang disediakan sebagai aplikasi demo untuk automation testing menggunakan Appium.

---

## Struktur Folder

```text
AppiumProject_Android_UAS/
├── pom.xml
├── README.md
├── TEST_CASES.md
├── copy-apk.sh
├── apps/
│   ├── Android_Appium_Demo.apk
│   └── PASTE_APK_HERE.txt
├── resources/
│   ├── config.properties
│   └── testng.xml
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   └── BaseTest.java
│   │   ├── pages/
│   │   │   ├── BasePage.java
│   │   │   ├── MainPage.java
│   │   │   ├── NavigationPage.java
│   │   │   └── UiValidationPage.java
│   │   └── utils/
│   │       ├── ConfigReader.java
│   │       └── ExtentReportManager.java
│   └── test/java/
│       └── tests/
│           ├── LaunchTest.java
│           ├── HomeTest.java
│           ├── InteractionTest.java
│           └── UiValidationTest.java
└── test-output/
    └── screenshots/
```

---

## Prerequisites

Pastikan software berikut sudah tersedia di Mac:

1. Java JDK 11 atau 17
2. Maven
3. Node.js dan npm
4. Android Studio
5. Android SDK Platform Tools
6. Appium Server
7. Appium UiAutomator2 Driver
8. IntelliJ IDEA Community Edition
9. Appium Inspector

Cek instalasi dasar:

```bash
java -version
mvn -version
node -v
npm -v
adb version
```

Jika `adb` belum terbaca, tambahkan Android SDK ke PATH:

```bash
echo 'export ANDROID_HOME=$HOME/Library/Android/sdk' >> ~/.zshrc
echo 'export PATH=$PATH:$ANDROID_HOME/platform-tools' >> ~/.zshrc
source ~/.zshrc
```

---

## Instalasi dan Konfigurasi Appium

Install Appium Server:

```bash
npm install -g appium
```

Cek versi Appium:

```bash
appium -v
```

Install driver Android UiAutomator2:

```bash
appium driver install uiautomator2
```

Cek driver yang sudah terinstall:

```bash
appium driver list --installed
```

Install Appium Doctor:

```bash
npm install -g appium-doctor
```

Cek konfigurasi Android:

```bash
appium-doctor --android
```

---

## Setup Emulator Android

Buka Android Studio:

```text
Android Studio > Device Manager > Create Virtual Device
```

Pilih device, misalnya:

```text
Phone > Pixel 6 / Pixel 7 / Pixel 8
```

Pilih API level yang tersedia, kemudian jalankan emulator.

Cek emulator:

```bash
adb devices
```

Output yang diharapkan:

```text
List of devices attached
emulator-5554   device
```

Cek versi Android emulator:

```bash
adb shell getprop ro.build.version.release
```

Output pada setup ini:

```text
15
```

---

## Konfigurasi Project

File konfigurasi berada di:

```text
resources/config.properties
```

Isi konfigurasi yang digunakan:

```properties
platformName=Android
automationName=UiAutomator2
deviceName=emulator-5554
platformVersion=15
appPackage=com.skill2lead.appiumdemo
appActivity=com.skill2lead.appiumdemo.MainActivity
appPath=apps/Android_Appium_Demo.apk
implicitWait=10
explicitWait=15
appiumServerUrl=http://127.0.0.1:4723
```

### Menyalin APK

Project ZIP ini menyediakan folder `apps/`, tetapi APK harus Anda copy sendiri dari repo `AndroidDemoApp` yang sudah Anda clone.

Jika posisi folder seperti ini:

```text
AndroidDemoApp/
AppiumProject_Android_UAS/
```

Jalankan dari folder `AppiumProject_Android_UAS`:

```bash
./copy-apk.sh ../AndroidDemoApp/Android_Appium_Demo.apk
```

Atau copy manual:

```bash
cp ../AndroidDemoApp/Android_Appium_Demo.apk apps/Android_Appium_Demo.apk
```

Pastikan file ini ada:

```text
apps/Android_Appium_Demo.apk
```

---

## Cara Menjalankan Test

### 1. Pastikan emulator aktif

```bash
adb devices
```

Pastikan muncul:

```text
emulator-5554   device
```

### 2. Jalankan Appium Server

Buka terminal baru:

```bash
appium
```

Biarkan terminal Appium tetap berjalan.

### 3. Jalankan test via terminal

Masuk ke folder project:

```bash
cd AppiumProject_Android_UAS
```

Jalankan:

```bash
mvn clean test
```

### 4. Jalankan test via IntelliJ IDEA

1. Buka IntelliJ IDEA
2. Pilih **Open**
3. Pilih folder `AppiumProject_Android_UAS`
4. Tunggu Maven download dependency
5. Klik kanan `resources/testng.xml`
6. Pilih **Run**

---

## Test Case

| TC-ID | Nama Test Case | Fitur | Expected Result |
|---|---|---|---|
| TC001 | Aplikasi berhasil dibuka | Launch App | Package aktif sesuai `com.skill2lead.appiumdemo` |
| TC002 | Activity utama tampil | Launch App | Activity aktif adalah `MainActivity` |
| TC003 | Halaman utama menampilkan UI | Home Screen | Elemen UI dan text berhasil terdeteksi |
| TC004 | Halaman utama memiliki elemen clickable | UI Interaction | Minimal ada satu elemen clickable |
| TC005 | Klik elemen tidak menyebabkan crash | UI Interaction | Aplikasi tetap berjalan pada package yang benar |
| TC006 | Tombol Back dan re-activate aplikasi | Edge Case | Aplikasi bisa diaktifkan kembali |
| TC007 | Validasi konten UI readable | UI Validation | TextView tersedia dan memiliki konten readable |

Detail lengkap tersedia di file:

```text
TEST_CASES.md
```

---

## Penjelasan Script

### `BaseTest.java`

Berfungsi sebagai class utama untuk setup dan teardown Appium. Class ini:

- Membaca konfigurasi dari `config.properties`
- Membuat `AndroidDriver`
- Menggunakan `UiAutomator2Options`
- Mengatur implicit wait
- Membuka Appium session
- Menutup driver setelah test selesai
- Menghubungkan test dengan ExtentReports

### `ConfigReader.java`

Utility untuk membaca file konfigurasi:

- `getValue(String key)` untuk mengambil string
- `getIntValue(String key)` untuk mengambil integer
- `getBooleanValue(String key)` untuk mengambil boolean

### `ExtentReportManager.java`

Mengatur report HTML:

- Membuat file `ExtentReport.html`
- Menulis log INFO, PASS, FAIL
- Mengambil screenshot saat test gagal
- Menyimpan screenshot ke folder `test-output/screenshots/`

### `BasePage.java`

Class dasar Page Object Model. Berisi reusable method:

- `click()`
- `type()`
- `clearAndType()`
- `getText()`
- `isDisplayed()`
- `waitForVisible()`
- `waitForClickable()`

### `MainPage.java`

Mewakili halaman utama aplikasi. Method penting:

- `isCorrectPackageOpened()`
- `getCurrentActivity()`
- `hasVisibleElements()`
- `getVisibleTexts()`
- `hasClickableElement()`
- `tapFirstClickableElement()`

### `NavigationPage.java`

Mewakili navigasi umum Android:

- `pressBack()`
- `activateApp()`
- `isAppStillOpened()`

### `UiValidationPage.java`

Digunakan untuk validasi UI:

- `countTextViews()`
- `countButtons()`
- `hasReadableTextContent()`

### Test Classes

- `LaunchTest.java` menjalankan TC001 dan TC002
- `HomeTest.java` menjalankan TC003 dan TC004
- `InteractionTest.java` menjalankan TC005 dan TC006
- `UiValidationTest.java` menjalankan TC007

---

## Report dan Screenshot

Report HTML setelah test selesai:

```text
test-output/ExtentReport.html
```

Screenshot ketika test gagal:

```text
test-output/screenshots/
```

Buka report dengan browser:

```bash
open test-output/ExtentReport.html
```

---

## Troubleshooting

### 1. `adb: command not found`

Tambahkan Android SDK ke PATH:

```bash
echo 'export ANDROID_HOME=$HOME/Library/Android/sdk' >> ~/.zshrc
echo 'export PATH=$PATH:$ANDROID_HOME/platform-tools' >> ~/.zshrc
source ~/.zshrc
```

### 2. `adb devices` kosong

Jalankan emulator dari Android Studio:

```text
Android Studio > Device Manager > Play
```

Lalu:

```bash
adb kill-server
adb start-server
adb devices
```

### 3. APK tidak ditemukan

Pastikan file APK ada di:

```text
apps/Android_Appium_Demo.apk
```

Copy APK:

```bash
cp ../AndroidDemoApp/Android_Appium_Demo.apk apps/Android_Appium_Demo.apk
```

### 4. Appium server tidak jalan

Jalankan:

```bash
appium
```

Pastikan server aktif di:

```text
http://127.0.0.1:4723
```

### 5. UiAutomator2 belum terinstall

Jalankan:

```bash
appium driver install uiautomator2
```

### 6. Maven dependency gagal download

Cek koneksi internet, lalu jalankan:

```bash
mvn clean install -U
```

---

## Checklist Submit UAS

Sebelum submit ke elearning, pastikan:

- [ ] Project automation Appium sudah berjalan
- [ ] File APK ada di folder `apps/`
- [ ] Project sudah di-upload ke GitHub
- [ ] `README.md` sudah lengkap
- [ ] Report test berhasil dibuat
- [ ] Video presentasi 15-20 menit sudah dibuat
- [ ] Video menampilkan wajah dan suara jelas
- [ ] Video di-upload ke Google Drive
- [ ] Google Drive sudah diset public / anyone with the link
- [ ] Link GitHub dan link Google Drive dilampirkan di elearning

---

## Catatan Presentasi

Saat presentasi, jelaskan urutan berikut:

1. Instalasi Java, Maven, Android Studio, Node.js, Appium
2. Konfigurasi emulator Android
3. Konfigurasi Appium dan UiAutomator2
4. Penjelasan aplikasi Skill2Lead AndroidDemoApp
5. Penjelasan struktur project Appium
6. Penjelasan test case TC001 sampai TC007
7. Demo menjalankan `mvn clean test`
8. Tampilkan hasil `ExtentReport.html`
