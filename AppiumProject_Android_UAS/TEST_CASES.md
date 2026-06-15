# Test Cases - UAS Kualitas Perangkat Lunak

| TC-ID | Nama Test Case | Fitur | Precondition | Steps | Expected Result | Status |
|---|---|---|---|---|---|---|
| TC001 | Aplikasi berhasil dibuka | Launch App | Emulator aktif, Appium Server aktif, APK tersedia | 1. Jalankan automation test 2. Appium membuka APK 3. Cek current package | Package aktif sama dengan `com.skill2lead.appiumdemo` | Diisi saat eksekusi |
| TC002 | Activity utama tampil | Launch App | Aplikasi sudah terbuka | 1. Ambil current activity 2. Bandingkan dengan MainActivity | Activity aktif adalah MainActivity | Diisi saat eksekusi |
| TC003 | Halaman utama menampilkan UI | Home Screen | Aplikasi berada di halaman utama | 1. Ambil semua elemen UI 2. Ambil semua text yang tampil | Elemen UI dan text berhasil terdeteksi | Diisi saat eksekusi |
| TC004 | Halaman utama memiliki elemen clickable | UI Interaction | Halaman utama tampil | 1. Cari elemen dengan property clickable true 2. Validasi jumlah elemen | Minimal ada satu elemen clickable | Diisi saat eksekusi |
| TC005 | Klik elemen tidak menyebabkan crash | UI Interaction | Halaman utama tampil dan ada elemen clickable | 1. Klik elemen clickable pertama 2. Cek aplikasi tetap berada di package yang benar | Aplikasi tetap berjalan dan tidak crash | Diisi saat eksekusi |
| TC006 | Tombol Back dan re-activate aplikasi | Edge Case | Aplikasi terbuka | 1. Tekan tombol Back 2. Aktifkan ulang aplikasi 3. Cek package dan konten layar | Aplikasi dapat dibuka kembali dan konten tampil | Diisi saat eksekusi |
| TC007 | Validasi konten UI readable | UI Validation | Aplikasi terbuka | 1. Hitung TextView 2. Cek text readable | TextView tersedia dan memiliki konten yang dapat dibaca | Diisi saat eksekusi |

