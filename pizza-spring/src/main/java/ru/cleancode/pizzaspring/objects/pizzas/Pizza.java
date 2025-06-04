package ru.cleancode.pizzaspring.objects.pizzas;

import lombok.Getter;
import lombok.Setter;
import ru.cleancode.pizzaspring.objects.Dish;
import ru.cleancode.pizzaspring.objects.Ingredient;
import ru.cleancode.pizzaspring.objects.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Абстрактный класс, представляющий базовую пиццу.
 */
@Getter
public abstract class Pizza implements Dish {

    /**
     * Название пиццы
     */
    protected final String name;

    /**
     * Размер пиццы
     */
    protected final PizzaSize size;

    /**
     * Цена пиццы
     */
    protected final BigDecimal price;

    /**
     * Список ингредиентов пиццы
     */
    @Setter
    protected List<Ingredient> ingredients;

    public Pizza(String name, PizzaSize size, BigDecimal price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }
}