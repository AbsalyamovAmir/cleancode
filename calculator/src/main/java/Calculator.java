/**
 * Калькулятор, выполняющий арифметические операции.
 */
public class Calculator {
    private final HistoryManager historyManager;

    /**
     * Калькулятор, выполняющий арифметические операции.
     *
     * @param historyManager менеджер истории операций
     */
    public Calculator(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    /**
     * Вычисляет результат математического выражения.
     *
     * @param expression математическое выражение
     * @return результат вычисления
     * @throws IllegalArgumentException если выражение невалидно
     */
    public double calculate(String expression) throws IllegalArgumentException {
        double result = OperationEvaluator.evaluate(expression);
        historyManager.saveOperation(expression, result);
        return result;
    }
}
