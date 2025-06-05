package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    private static EnvLoader instance;
    private final Properties props;

    // Constructor privado para evitar instanciación externa
    private EnvLoader() {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/.env")) {
            props.load(fis);
            System.out.println("✅ .env file loaded successfully.");
        } catch (IOException e) {
            System.out.println("❌ Could not load .env file: " + e.getMessage());
        }
    }

    // Método público para acceder a la instancia única
    public static EnvLoader getInstance() {
        if (instance == null) {
            instance = new EnvLoader();
        }
        return instance;
    }

    // Método para obtener una variable de entorno
    public String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            System.out.println("⚠️ Environment variable not found: " + key);
        }
        return value;
    }
}
