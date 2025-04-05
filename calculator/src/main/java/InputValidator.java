import java.util.regex.Pattern;

/**
 * Валидирует ввод пользователя.
 */
public class InputValidator {
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("^[\\d\\s+\\-*/^().]+$");

    /**
     * Проверяет, является ли строка валидным математическим выражением.
     *
     * @param input ввод пользователя
     * @throws IllegalArgumentException если выражение явялется не математическим выражением.
     */
    public static void isValidExpression(String input) {
        if (input == null || !EXPRESSION_PATTERN.matcher(input).matches() || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверный ввод. Разрешены только цифры, операторы (+-*/^) и скобки.");
        }
    }
}
