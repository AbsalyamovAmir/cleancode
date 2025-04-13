package ru.cleancode.PizzaProject.exceptions;

import java.text.MessageFormat;

public class KitchenTypeException extends Throwable {
    public KitchenTypeException(Class<?> Class) {
        super(MessageFormat.format("Получен список блюд, которые невозможно выполнить в {0}", Class.getName()));
    }
}
