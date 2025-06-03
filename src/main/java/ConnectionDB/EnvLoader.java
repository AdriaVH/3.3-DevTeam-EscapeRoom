package ConnectionDB;
import io.github.cdimascio.dotenv.Dotenv;  // Asegúrate de tener esta importación

public class EnvLoader {
    private static EnvLoader instance;
    private final Dotenv dotenv;

    private EnvLoader() {
        dotenv = Dotenv.configure()
                .ignoreIfMalformed()  // Ignorar errores de formato
                .ignoreIfMissing()    // No falla si falta el archivo .env
                .load();
    }

    public static synchronized EnvLoader getInstance() {
        if (instance == null) {
            instance = new EnvLoader();
        }
        return instance;
    }

    public String getDbUrl() {
        return dotenv.get("DB_URL");
    }

    public String getDbUser() {
        return dotenv.get("DB_USER");
    }

    public String getDbPassword() {
        return dotenv.get("DB_PASSWORD");
    }
}
