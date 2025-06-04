package ru.cleancode.pizzaspring.objects;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клиент
 */
@RequiredArgsConstructor
public class Client {

    /**
     * Наименование клиента
     */
    public final String name;

    /**
     * Список заказов клиента
     */
    private final List<Order> orders;

    @Override
    public String toString() {
        return String.format("Клиент %s, заказал следующие блюда: %s с общей стоимостью - %f", name,
                orders.stream()
                        .flatMap(order -> order.getDishes().stream())
                        .map(Dish::getDishName)
                        .collect(Collectors.joining(", ")),
                orders.stream()
                        .flatMap(order -> order.getDishes().stream())
                        .map(Dish::getDishPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
