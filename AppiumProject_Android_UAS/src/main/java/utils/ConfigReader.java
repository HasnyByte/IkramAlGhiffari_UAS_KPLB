package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Utility class untuk membaca konfigurasi dari file resources/config.properties.
 */
public final class ConfigReader {
    private static final String CONFIG_PATH = "resources/config.properties";
    private static final Properties properties = new Properties();

    static {
        loadConfig();
    }

    private ConfigReader() {
        // Utility class tidak perlu dibuat object.
    }

    /**
     * Membaca file config.properties dari folder resources.
     */
    private static void loadConfig() {
        Path path = Paths.get(CONFIG_PATH);

        if (!Files.exists(path)) {
            throw new RuntimeException("File config.properties tidak ditemukan di: " + path.toAbsolutePath());
        }

        try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Gagal membaca config.properties: " + e.getMessage(), e);
        }
    }

    /**
     * Mengambil value dari key tertentu.
     *
     * @param key nama key pada config.properties
     * @return value dalam bentuk String
     */
    public static String getValue(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Key tidak ditemukan di config.properties: " + key);
        }

        return value.trim();
    }

    /**
     * Mengambil value String dengan default jika key kosong.
     *
     * @param key nama key
     * @param defaultValue nilai default
     * @return value dari config atau default
     */
    public static String getValue(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return value == null || value.trim().isEmpty() ? defaultValue : value.trim();
    }

    /**
     * Mengambil value integer dari config.properties.
     *
     * @param key nama key
     * @return value dalam bentuk int
     */
    public static int getIntValue(String key) {
        try {
            return Integer.parseInt(getValue(key));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Value untuk key " + key + " harus berupa angka.", e);
        }
    }

    /**
     * Mengambil value boolean dari config.properties.
     *
     * @param key nama key
     * @return value dalam bentuk boolean
     */
    public static boolean getBooleanValue(String key) {
        return Boolean.parseBoolean(getValue(key, "false"));
    }
}
