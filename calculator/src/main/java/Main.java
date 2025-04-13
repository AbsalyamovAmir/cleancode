import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String HISTORY_DIR = "history";
    private static final String LOG_DIR = "log";
    private static final int MAX_FILE_SIZE_MB = 100;
    private static final String EVALUATE_EXPRESSION = "1";
    private static final String LOAD_HISTORY = "2";
    private static final String CLEAR_HISTORY = "3";
    private static final String EXIT = "4";

    private static final Scanner scanner = new Scanner(System.in);
    private static HistoryManager historyManager;

    public static void main(String[] args) {
        FileManager historyFileManager = new FileManager(HISTORY_DIR, "operationHistory", MAX_FILE_SIZE_MB);
        FileManager logFileManager = new FileManager(LOG_DIR, "operationLog", MAX_FILE_SIZE_MB);

        historyManager = new HistoryManager(historyFileManager);
        Logger logger = new Logger(logFileManager);


        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            try {
                switch (input.toLowerCase()) {
                    case EVALUATE_EXPRESSION:
                        performCalculation(logger);
                        break;
                    case LOAD_HISTORY:
                        handleHistoryCommand();
                        break;
                    case CLEAR_HISTORY:
                        handleClearCommand();
                        break;
                    case EXIT:
                        System.out.println("Выход из программы. Пока!");
                        return;
                    default:
                        throw new IllegalArgumentException("Неизвестная команда. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
                logger.logError("Ошибка выполнения команды: " + input, e);
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Калькулятор ===");
        System.out.println("1. Выполнить вычисление");
        System.out.println("2. Просмотреть историю");
        System.out.println("3. Очистить историю");
        System.out.println("4. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void performCalculation(Logger logger) {
        Calculator calculator = new Calculator(historyManager);
        System.out.print("Введите выражение (например, (2+3)*4^2): ");
        String expression = scanner.nextLine().trim();

        handleCalculation(expression, calculator, logger);
    }

    private static void handleHistoryCommand() throws IOException {
        String history = historyManager.readHistory();
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            if (history.contains("--- File 2 ---")) {
                System.out.println("Внимание: Найдено несколько файлов истории. Показаны все данные...");
            }
            System.out.println("История операций:");
            System.out.println(history);
        }
    }

    private static void handleClearCommand() throws IOException {
        historyManager.clearHistory();
        System.out.println("История очищена.");
    }

    private static void handleCalculation(String input, Calculator calculator, Logger logger) {
        try {
            InputValidator.isValidExpression(input);
            double result = calculator.calculate(input);
            System.out.printf(String.format("Результат: %1$f", result));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка расчета: " + e.getMessage());
            logger.logError("Ошибка вычисления выражения: " + input, e);
        } catch (ArithmeticException e) {
            System.err.println("Арифметическая ошибка: " + e.getMessage());
            logger.logError("Арифметическая ошибка выражения: " + input, e);
        }
    }
}
