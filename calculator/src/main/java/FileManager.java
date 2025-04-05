import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Менеджер, управляющий созданием и записью в файлы с ограничением размера.
 */
public class FileManager {
    private final String directory;
    private final String filePrefix;
    private final long maxSizeBytes;
    private int fileCounter = 1;

    /**
     * Менеджер, управляющий созданием и записью в файлы с ограничением размера.
     *
     * @param directory директория для хранения файлов
     * @param filePrefix префикс имени файлов
     * @param maxSizeMB максимальный размер файла в мегабайтах
     */
    public FileManager(String directory, String filePrefix, int maxSizeMB) {
        this.directory = directory;
        this.filePrefix = filePrefix;
        this.maxSizeBytes = maxSizeMB * 1024L * 1024L;
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Записывает данные в текущий файл, создает новый при превышении размера.
     *
     * @param data данные для записи
     * @throws IOException если произошла ошибка записи
     */
    public void writeToFile(String data) throws IOException {
        String currentFileName = getCurrentFileName();
        File currentFile = new File(currentFileName);

        if (currentFile.exists() && currentFile.length() >= maxSizeBytes) {
            fileCounter++;
            currentFileName = getCurrentFileName();
        }

        try (FileWriter writer = new FileWriter(currentFileName, true)) {
            writer.write(data + System.lineSeparator());
        }
    }

    /**
     * Читает все данные из всех файлов в директории.
     *
     * @return содержимое всех файлов
     * @throws IOException если произошла ошибка чтения
     */
    public String readAllFiles() throws IOException {
        StringBuilder content = new StringBuilder();
        int counter = 1;

        while (true) {
            String fileName = directory + File.separator + filePrefix + "_" + counter + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                break;
            }

            if (counter > 1) {
                content.append("\n--- File ").append(counter).append(" ---\n");
            }

            content.append(Files.readString(Path.of(fileName)));
            counter++;
        }

        return content.toString();
    }

    /**
     * Очищает все файлы в директории.
     *
     * @throws IOException если произошла ошибка удаления
     */
    public void clearAllFiles() throws IOException {
        int counter = 1;
        while (true) {
            String fileName = directory + File.separator + filePrefix + "_" + counter + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                break;
            }
            Files.deleteIfExists(Paths.get(fileName));
            counter++;
        }
        fileCounter = 1;
    }

    private String getCurrentFileName() {
        return directory + File.separator + filePrefix + "_" + fileCounter + ".txt";
    }
}