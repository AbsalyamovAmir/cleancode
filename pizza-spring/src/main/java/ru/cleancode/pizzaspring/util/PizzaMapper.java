package ru.cleancode.pizzaspring.util;

import lombok.experimental.UtilityClass;
import ru.cleancode.pizzaspring.model.DishEntity;
import ru.cleancode.pizzaspring.objects.PizzaSize;
import ru.cleancode.pizzaspring.objects.pizzas.ChicagoDeepDish;
import ru.cleancode.pizzaspring.objects.pizzas.Margherita;
import ru.cleancode.pizzaspring.objects.pizzas.Okonomiyaki;
import ru.cleancode.pizzaspring.objects.pizzas.Pizza;

@UtilityClass
public class PizzaMapper {

    //TODO пока такой костыль
    public Pizza convertToPizza(DishEntity dish) {
        Pizza pizza;
        String name = dish.getName().toLowerCase();
        if (name.contains("маргарит") || name.contains("margherita")) {
            pizza = new Margherita(PizzaSize.MEDIUM, dish.getPrice());
        } else if (name.contains("чикаг") || name.contains("chicago")) {
            pizza = new ChicagoDeepDish(PizzaSize.MEDIUM, dish.getPrice());
        } else if (name.contains("окономияки") || name.contains("okonomiyaki")) {
            pizza = new Okonomiyaki(PizzaSize.MEDIUM, dish.getPrice());
        } else {
            throw new IllegalArgumentException("Unknown pizza type for dish: " + dish.getName());
        }
        return pizza;
    }
}
