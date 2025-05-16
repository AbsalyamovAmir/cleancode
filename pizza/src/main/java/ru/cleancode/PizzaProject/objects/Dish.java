package ru.cleancode.PizzaProject.objects;

import java.math.BigDecimal;

/**
 * Блюдо
 */
public interface Dish {

    /**
     * Наименование блюда
     *
     * @return наименование блюда
     */
    String getDishName();

    /**
     * Цена блюда
     *
     * @return Цена блюда
     */
    BigDecimal getDishPrice();
}
