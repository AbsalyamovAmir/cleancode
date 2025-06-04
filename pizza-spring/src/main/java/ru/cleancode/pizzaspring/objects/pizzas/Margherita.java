package ru.cleancode.pizzaspring.objects.pizzas;

import ru.cleancode.pizzaspring.objects.Ingredient;
import ru.cleancode.pizzaspring.objects.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Пицца маргарита
 */
public class Margherita extends Pizza {
    public Margherita(PizzaSize size, BigDecimal price) {
        super("Маргарита", size, price);
    }

    public void setIngredients() {
        Ingredient dough = Ingredient.builder().name("Тесто").weight(250).build();
        Ingredient mozzarella = Ingredient.builder().name("Моцарелла").weight(100).build();
        Ingredient tomatoPaste = Ingredient.builder().name("Томатная паста").weight(100).build();
        switch (size) {
            case PizzaSize.SMALL:
                dough.setWeight(dough.getWeight() - 50);
                mozzarella.setWeight(mozzarella.getWeight() - 20);
                tomatoPaste.setWeight(tomatoPaste.getWeight() - 20);
                break;
            case PizzaSize.LARGE:
                dough.setWeight(dough.getWeight() + 50);
                mozzarella.setWeight(mozzarella.getWeight() + 20);
                tomatoPaste.setWeight(tomatoPaste.getWeight() + 20);
        }
        super.setIngredients(List.of(dough, mozzarella, tomatoPaste));
    }

    @Override
    public String getDishName() {
        return name;
    }

    @Override
    public BigDecimal getDishPrice() {
        BigDecimal price = super.getPrice();
        switch (size) {
            case PizzaSize.SMALL -> price = price.subtract(BigDecimal.valueOf(50));
            case PizzaSize.LARGE -> price = price.add(BigDecimal.valueOf(50));
        }
        return price;
    }
}
