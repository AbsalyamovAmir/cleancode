package ru.cleancode.pizzaspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cleancode.pizzaspring.objects.pizzas.Pizza;
import ru.cleancode.pizzaspring.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/restaurant/{id}")
    public List<Pizza> getMenuByRestaurant(@PathVariable Long id) {
        return menuService.getMenuByRestaurant(id);
    }

    @GetMapping("/kitchen/{id}")
    public List<Pizza> getMenuByKitchenType(@PathVariable Long id) {
        return menuService.getMenuByKitchenType(id);
    }

    @GetMapping("/pizza/{id}")
    public Pizza getPizzaDetails(@PathVariable Long id) {
        return menuService.getPizzaDetails(id);
    }
}
