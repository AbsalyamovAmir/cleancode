package ru.cleancode.PizzaProject.objects.pizzas;

import lombok.Getter;
import ru.cleancode.PizzaProject.objects.Ingredient;

import java.util.List;

/**
 * Базовый декоратор для пиццы
 */
@Getter
public abstract class PizzaDecorator extends Pizza {
    protected final Pizza pizza;

    protected PizzaDecorator(Pizza pizza) {
        super(pizza.getName(), pizza.getSize(), pizza.getPrice());
        this.pizza = pizza;
        this.ingredients = pizza.getIngredients();
    }

    public List<Ingredient> getIngredients() {
        return pizza.getIngredients();
    }
}
