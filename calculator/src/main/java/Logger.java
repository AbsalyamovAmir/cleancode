import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Логер ошибок в файл.
 */
public class Logger {
    private final FileManager fileManager;
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Логер ошибок в файл.
     *
     * @param fileManager менеджер файлов для логов
     */
    public Logger(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Логирует сообщение об ошибке.
     *
     * @param message сообщение об ошибке
     * @param exception исключение (может быть null)
     */
    public void logError(String message, Exception exception) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] ERROR: %s", timestamp, message);

            if (exception != null) {
                logEntry += "\nException: " + exception;
            }

            fileManager.writeToFile(logEntry);
        } catch (IOException e) {
            System.err.println("Не удалось залогировать ошибку: " + e.getMessage());
        }
    }
}
