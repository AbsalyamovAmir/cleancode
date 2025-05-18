package ru.cleancode.pizzaspring.objects;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Заказ
 */
@Data
@Builder
public class Order {
    /**
     * Блюда
     */
    public List<Dish> dishes;
}
