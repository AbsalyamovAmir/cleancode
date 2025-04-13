package ru.cleancode.PizzaProject.exceptions;

public class PizzaPriceException extends Throwable {
    public PizzaPriceException() {
        super("Введена неверная стоимость пиццы");
    }
}
