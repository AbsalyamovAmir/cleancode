package ru.cleancode.pizzaspring.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cleancode.pizzaspring.model.DishEntity;
import ru.cleancode.pizzaspring.model.RestaurantEntity;
import ru.cleancode.pizzaspring.objects.pizzas.Pizza;
import ru.cleancode.pizzaspring.repository.DishRepository;
import ru.cleancode.pizzaspring.service.MenuService;
import ru.cleancode.pizzaspring.util.PizzaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final DishRepository dishRepository;

    @Override
    public List<Pizza> getMenuByRestaurant(Long restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(PizzaMapper::convertToPizza)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pizza> getMenuByKitchenType(Long kitchenTypeId) {
        return dishRepository.findByKitchenTypeId(kitchenTypeId)
                .stream()
                .map(PizzaMapper::convertToPizza)
                .collect(Collectors.toList());
    }

    @Override
    public Pizza getPizzaDetails(Long dishId) {
        return dishRepository.findById(dishId)
                .map(PizzaMapper::convertToPizza)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    @Override
    public DishEntity createPizza(Pizza Pizza, RestaurantEntity restaurant) {
        DishEntity dish = new DishEntity();
        dish.setName(Pizza.getName());
        dish.setPrice(Pizza.getPrice());
        return dishRepository.save(dish);
    }
}
