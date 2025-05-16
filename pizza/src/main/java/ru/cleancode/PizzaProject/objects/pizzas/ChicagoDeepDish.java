package ru.cleancode.PizzaProject.objects.pizzas;

import ru.cleancode.PizzaProject.objects.Ingredient;
import ru.cleancode.PizzaProject.objects.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Чикагская глубокая пицца
 */
public class ChicagoDeepDish extends Pizza {

    public ChicagoDeepDish(PizzaSize size, BigDecimal price) {
        super("Чикагская глубокая пицца", size, price);
    }

    public void setIngredients() {
        Ingredient dough = Ingredient.builder().name("Толстое тесто").weight(300).build();
        Ingredient pepperoni = Ingredient.builder().name("Пепперони").weight(100).build();
        Ingredient bbqSouce = Ingredient.builder().name("Соус барбекю").weight(200).build();
        switch (size) {
            case PizzaSize.SMALL:
                dough.setWeight(dough.getWeight() - 51);
                pepperoni.setWeight(pepperoni.getWeight() - 20);
                bbqSouce.setWeight(bbqSouce.getWeight() - 20);
                break;
            case PizzaSize.LARGE:
                dough.setWeight(dough.getWeight() + 50);
                pepperoni.setWeight(pepperoni.getWeight() + 20);
                bbqSouce.setWeight(bbqSouce.getWeight() + 20);
        }
        super.setIngredients(List.of(dough, pepperoni, bbqSouce));
    }

    @Override
    public String getDishName() {
        return name;
    }

    @Override
    public BigDecimal getDishPrice() {
        BigDecimal price = super.getPrice();
        switch (size) {
            case PizzaSize.SMALL -> price = price.subtract(BigDecimal.valueOf(60));
            case PizzaSize.LARGE -> price = price.add(BigDecimal.valueOf(60));
        }
        return price;
    }
}
