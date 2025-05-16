package ru.cleancode.PizzaProject.objects.kitchens;

import ru.cleancode.PizzaProject.exceptions.KitchenTypeException;
import ru.cleancode.PizzaProject.exceptions.OrderLimitExceededException;
import ru.cleancode.PizzaProject.objects.Order;

import java.util.List;

/**
 * Базовый класс кухни
 */
public abstract class Kitchen {

    /**
     * Лимит заказов
     */
    protected final int ordersLimit;

    protected Kitchen(int ordersLimit) {
        this.ordersLimit = ordersLimit;
    }

    /**
     * Выполнить работу по заказу
     *
     * @param orders заказ
     * @throws OrderLimitExceededException превышение количества заказов
     * @throws KitchenTypeException        неподходящие типы блюд
     */
    public abstract void doJob(List<Order> orders) throws OrderLimitExceededException, KitchenTypeException;
}
