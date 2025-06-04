package ru.cleancode.pizzaspring.service;

import org.springframework.stereotype.Service;
import ru.cleancode.pizzaspring.model.DishEntity;
import ru.cleancode.pizzaspring.model.RestaurantEntity;
import ru.cleancode.pizzaspring.objects.pizzas.Pizza;

import java.util.List;

@Service
public interface MenuService {
    List<Pizza> getMenuByRestaurant(Long restaurantId);
    List<Pizza> getMenuByKitchenType(Long kitchenTypeId);
    Pizza getPizzaDetails(Long dishId);
    DishEntity createPizza(Pizza pizzaDTO, RestaurantEntity restaurant);
}