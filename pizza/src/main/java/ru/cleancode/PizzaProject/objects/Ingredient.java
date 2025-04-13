package ru.cleancode.PizzaProject.objects;

import lombok.Builder;
import lombok.Data;

/**
 * Ингредиент
 */
@Builder
@Data
public class Ingredient {
    /**
     * Наименование ингредиента
     */
    private String name;

    /**
     * Вес ингредиента
     */
    private int weight;
}
