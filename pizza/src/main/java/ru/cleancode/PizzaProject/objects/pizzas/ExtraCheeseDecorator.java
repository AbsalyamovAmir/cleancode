package ru.cleancode.PizzaProject.objects.pizzas;

import ru.cleancode.PizzaProject.objects.Ingredient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Декоратор для добавки сыра
 */
public class ExtraCheeseDecorator extends PizzaDecorator {
    public ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>(pizza.getIngredients());
        ingredients.add(Ingredient.builder()
                .name("Дополнительный сыр")
                .weight(50)
                .build());
        return ingredients;
    }

    @Override
    public String getDishName() {
        return String.format("%s + с дополнительным сыром", getName());
    }

    @Override
    public BigDecimal getDishPrice() {
        return getPrice().add(new BigDecimal(50));
    }
}
