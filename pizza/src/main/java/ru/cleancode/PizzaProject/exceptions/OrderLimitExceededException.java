package ru.cleancode.PizzaProject.exceptions;

import java.text.MessageFormat;

public class OrderLimitExceededException extends Throwable {
    public OrderLimitExceededException(int size, int countLimitDishes) {
        super(MessageFormat.format("Превышен лимит блюд на кухне. Заказано {0} при лимите {1}", size, countLimitDishes));
    }
}
