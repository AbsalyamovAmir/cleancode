package ru.cleancode.PizzaProject.objects.kitchens;

import org.apache.logging.log4j.Logger;
import ru.cleancode.PizzaProject.exceptions.KitchenTypeException;
import ru.cleancode.PizzaProject.exceptions.OrderLimitExceededException;
import ru.cleancode.PizzaProject.objects.Dish;
import ru.cleancode.PizzaProject.objects.Order;
import ru.cleancode.PizzaProject.objects.pizzas.Pizza;
import ru.cleancode.PizzaProject.utility.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Пиццерия Додо пицца
 */
public class DodoPizza extends Kitchen implements Pizzeria {

    private static final Logger log = LoggerFactory.getLogger(DodoPizza.class);

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
