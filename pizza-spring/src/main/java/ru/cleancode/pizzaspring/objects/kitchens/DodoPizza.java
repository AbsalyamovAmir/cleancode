package ru.cleancode.pizzaspring.objects.kitchens;

import lombok.extern.slf4j.Slf4j;
import ru.cleancode.pizzaspring.exceptions.KitchenTypeException;
import ru.cleancode.pizzaspring.exceptions.OrderLimitExceededException;
import ru.cleancode.pizzaspring.objects.Dish;
import ru.cleancode.pizzaspring.objects.Order;
import ru.cleancode.pizzaspring.objects.pizzas.Pizza;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Пиццерия Додо пицца
 */
@Slf4j
public class DodoPizza extends Kitchen implements Pizzeria {

    private static DodoPizza instance;

    private DodoPizza() {
        super(2);
    }

    public static DodoPizza getInstance() {
        if (instance == null) {
            instance = new DodoPizza();
        }
        return instance;
    }

    @Override
    public void doJob(List<Order> orders) throws OrderLimitExceededException, KitchenTypeException {
        if (orders.size() > ordersLimit) {
            throw new OrderLimitExceededException(orders.size(), ordersLimit);
        }
        for (Order order : orders) {
            List<Dish> dishes = order.getDishes();
            if (dishes.stream().anyMatch(dish -> !(dish instanceof Pizza))) {
                throw new KitchenTypeException(DodoPizza.class);
            }

            log.info(String.format("Выполнение заказа: %s", order.getDishes().stream()
                    .map(Dish::getDishName)
                    .collect(Collectors.joining(", "))));
        }

    }

    @Override
    public boolean isPizzeria() {
        return true;
    }
}
