import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Данный алгоритм нашел на просторах интернета.

/**
 * Вычисляет математические выражения с использованием алгоритма сортировочной станции.
 */
public class OperationEvaluator {
    /**
     * Таблица приоритетов операторов.
     * Ключ - оператор, значение - числовой приоритет (чем выше, тем приоритетнее).
     */
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("^", 4);
        PRECEDENCE.put("*", 3);
        PRECEDENCE.put("/", 3);
        PRECEDENCE.put("+", 2);
        PRECEDENCE.put("-", 2);
    }

    /**
     * Вычисляет значение математического выражения.
     *
     * @param expression математическое выражение в виде строки.
     *        Допустимые символы: цифры (0-9), операторы (+ - * / ^), скобки ( ) и точка для десятичных дробей.
     *        Пробелы в выражении игнорируются.
     * @return результат вычисления выражения как число с плавающей точкой.
     * @throws IllegalArgumentException если выражение содержит недопустимые символы или синтаксически неверно.
     * @throws ArithmeticException при делении на ноль.
     * @throws RuntimeException если выражение не может быть корректно вычислено.
     */
    public static double evaluate(String expression) {
        List<String> tokens = tokenize(expression);
        List<String> rpn = shuntingYard(tokens);
        return evaluateRPN(rpn);
    }

    /**
     * Разбивает строку выражения на токены (числа, операторы, скобки).
     *
     * @param expression входное математическое выражение.
     * @return список токенов в порядке их появления в выражении.
     * @throws IllegalArgumentException если выражение содержит недопустимые символы.
     */
    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("([0-9]+\\.?[0-9]*)|([+\\-*/^()])");
        Matcher matcher = pattern.matcher(expression.replaceAll("\\s", ""));

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        if (expression.replaceAll("\\s", "").length() != tokens.stream()
                .mapToInt(String::length).sum()) {
            throw new IllegalArgumentException("Выражение содержит недопустимые символы");
        }

        return tokens;
    }

    /**
     * Преобразует список токенов в инфиксной нотации в обратную польскую запись (ОПЗ)
     * с использованием алгоритма "Shunting Yard".
     *
     * @param tokens список токенов в инфиксной нотации.
     * @return список токенов в постфиксной нотации (ОПЗ).
     * @throws IllegalArgumentException если нарушена структура скобок.
     */
    private static List<String> shuntingYard(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("[0-9]+\\.?[0-9]*")) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Несбалансированные скобки");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                        (PRECEDENCE.get(token) <= PRECEDENCE.get(stack.peek()))) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new IllegalArgumentException("Несбалансированные скобки");
            }
            output.add(stack.pop());
        }

        return output;
    }

    /**
     * Проверяет, является ли токен оператором.
     *
     * @param token проверяемый токен.
     * @return true если токен является оператором, false в противном случае.
     */
    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    /**
     * Вычисляет значение выражения, представленного в обратной польской записи.
     *
     * @param rpn список токенов в ОПЗ.
     * @return результат вычисления.
     * @throws ArithmeticException при делении на ноль.
     * @throws RuntimeException если выражение не может быть корректно вычислено
     *         (неверное количество операндов или операторов).
     */
    private static double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn) {
            if (token.matches("[0-9]+\\.?[0-9]*")) {
                stack.push(Double.parseDouble(token));
            } else {
                if (stack.size() < 2) {
                    throw new RuntimeException("Недостаточно операндов для оператора " + token);
                }
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("Деление на ноль");
                        stack.push(a / b);
                        break;
                    case "^": stack.push(Math.pow(a, b)); break;
                    default:
                        throw new RuntimeException("Неизвестный оператор: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException("Ошибка в выражении: не все операторы были обработаны");
        }

        return stack.pop();
    }
}
