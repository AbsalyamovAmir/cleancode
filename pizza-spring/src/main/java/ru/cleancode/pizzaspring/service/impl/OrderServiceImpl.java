package ru.cleancode.pizzaspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cleancode.pizzaspring.model.*;
import ru.cleancode.pizzaspring.model.dto.DishDTO;
import ru.cleancode.pizzaspring.model.dto.OrderDTO;
import ru.cleancode.pizzaspring.objects.Client;
import ru.cleancode.pizzaspring.objects.Dish;
import ru.cleancode.pizzaspring.objects.Order;
import ru.cleancode.pizzaspring.repository.*;
import ru.cleancode.pizzaspring.service.OrderService;
import ru.cleancode.pizzaspring.util.PizzaMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public CustomerOrderEntity createOrder(OrderDTO orderDTO, Long customerId, Long restaurantId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        CustomerOrderEntity order = new CustomerOrderEntity();
        order.setCustomerEntity(customer);
        order.setRestaurantEntity(restaurant);
        order.setStatus("NEW");
        order.setTotalPrice(calculateTotalPrice(orderDTO));

        CustomerOrderEntity savedOrder = orderRepository.save(order);

        for (DishDTO dish : orderDTO.getDishes()) {
            DishEntity dishEntity = dishRepository.findByName(dish.getName())
                    .orElseThrow(() -> new RuntimeException("Dish not found: " + dish.getName()));

            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(savedOrder);
            item.setDish(dishEntity);
            item.setQuantity(1);
            item.setPriceAtOrder(dish.getPrice());

            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Client getCustomerWithOrders(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Order> orders = getCustomerOrders(customerId);

        return new Client(customer.getName(), orders);
    }

    private BigDecimal calculateTotalPrice(OrderDTO orderDTO) {
        return orderDTO.getDishes().stream()
                .map(DishDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order convertToOrderDTO(CustomerOrderEntity order) {
        List<Dish> dishes = order.getItems().stream()
                .map(OrderItemEntity::getDish)
                .map(PizzaMapper::convertToPizza)
                .collect(Collectors.toList());
        return Order.builder()
                .dishes(dishes)
                .build();
    }
}
