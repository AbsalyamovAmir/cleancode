package ru.cleancode.pizzaspring.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {
    public List<DishDTO> dishes;
}
