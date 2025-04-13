package ru.cleancode.PizzaProject.objects.pizzas;

import ru.cleancode.PizzaProject.objects.Ingredient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Декоратор для добавки пепперони
 */
public class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>(pizza.getIngredients());
        ingredients.add(Ingredient.builder()
                .name("Пепперони")
                .weight(80)
                .build());
        return ingredients;
    }

    @Override
    public String getDishName() {
        return String.format("%s + с дополнительным Пепперони", getName());
    }

    @Override
    public BigDecimal getDishPrice() {
        return getPrice().add(BigDecimal.valueOf(100));
    }
}
