import java.io.IOException;

/**
 * Менеджер, управляющий историей операций: сохранение, чтение и очистка.
 */
public class HistoryManager {
    private final FileManager fileManager;

    /**
     * Менеджер, управляющий историей операций: сохранение, чтение и очистка.
     *
     * @param fileManager менеджер файлов для истории
     */
    public HistoryManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Сохраняет операцию в историю.
     *
     * @param expression выражение
     * @param result результат вычисления
     */
    public void saveOperation(String expression, double result) {
        try {
            String record = String.format("%s = %.2f", expression, result);
            fileManager.writeToFile(record);
        } catch (IOException e) {
            System.err.println("Не удалось сохранить операцию в историю: " + e.getMessage());
        }
    }

    /**
     * Читает всю историю операций.
     *
     * @return строка с историей операций
     * @throws IOException если произошла ошибка чтения
     */
    public String readHistory() throws IOException {
        return fileManager.readAllFiles();
    }

    /**
     * Очищает историю операций.
     *
     * @throws IOException если произошла ошибка удаления
     */
    public void clearHistory() throws IOException {
        fileManager.clearAllFiles();
    }
}