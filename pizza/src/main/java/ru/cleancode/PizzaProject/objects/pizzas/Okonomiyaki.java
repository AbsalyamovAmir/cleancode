package ru.cleancode.PizzaProject.objects.pizzas;

import ru.cleancode.PizzaProject.objects.Ingredient;
import ru.cleancode.PizzaProject.objects.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Пицца Окономияки
 */
public class Okonomiyaki extends Pizza {
    public Okonomiyaki(PizzaSize size, BigDecimal price) {
        super("Пицца Окономияки", size, price);
    }

    public void setIngredients() {
        Ingredient dough = Ingredient.builder().name("Тесто").weight(200).build();
        Ingredient shrimps = Ingredient.builder().name("Креветки ").weight(100).build();
        Ingredient teriyaki = Ingredient.builder().name("Терияки").weight(200).build();
        switch (size) {
            case PizzaSize.SMALL:
                dough.setWeight(dough.getWeight() - 55);
                shrimps.setWeight(shrimps.getWeight() - 20);
                teriyaki.setWeight(teriyaki.getWeight() - 20);
                break;
            case PizzaSize.LARGE:
                dough.setWeight(dough.getWeight() + 50);
                shrimps.setWeight(shrimps.getWeight() + 20);
                teriyaki.setWeight(teriyaki.getWeight() + 20);
        }
        super.setIngredients(List.of(dough, shrimps, teriyaki));
    }

    @Override
    public String getDishName() {
        return name;
    }

    @Override
    public BigDecimal getDishPrice() {
        BigDecimal price = super.getPrice();
        switch (size) {
            case PizzaSize.SMALL -> price = price.subtract(BigDecimal.valueOf(70));
            case PizzaSize.LARGE -> price = price.add(BigDecimal.valueOf(70));
        }
        return price;
    }
}
