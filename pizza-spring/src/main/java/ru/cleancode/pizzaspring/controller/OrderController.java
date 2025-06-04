package ru.cleancode.pizzaspring.controller;

import org.springframework.web.bind.annotation.*;
import ru.cleancode.pizzaspring.model.dto.OrderDTO;
import ru.cleancode.pizzaspring.objects.Client;
import ru.cleancode.pizzaspring.objects.Order;
import ru.cleancode.pizzaspring.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Long createOrder(@RequestBody OrderDTO order,
                            @RequestParam Long customerId,
                            @RequestParam Long restaurantId) {
        return orderService.createOrder(order, customerId, restaurantId).getId();
    }

    @GetMapping("/customer/{id}")
    public List<Order> getCustomerOrders(@PathVariable Long id) {
        return orderService.getCustomerOrders(id);
    }

    @GetMapping("/customer/{id}/full")
    public Client getCustomerWithOrders(@PathVariable Long id) {
        return orderService.getCustomerWithOrders(id);
    }
}
