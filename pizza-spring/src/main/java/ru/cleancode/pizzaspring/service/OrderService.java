package ru.cleancode.pizzaspring.service;

import ru.cleancode.pizzaspring.model.CustomerOrderEntity;
import ru.cleancode.pizzaspring.model.dto.OrderDTO;
import ru.cleancode.pizzaspring.objects.Client;
import ru.cleancode.pizzaspring.objects.Order;

import java.util.List;

public interface OrderService {
    CustomerOrderEntity createOrder(OrderDTO orderDTO, Long customerId, Long restaurantId);
    List<Order> getCustomerOrders(Long customerId);
    Client getCustomerWithOrders(Long customerId);
}
