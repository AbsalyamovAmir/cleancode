package ru.cleancode.pizzaspring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "kitchen_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KitchenTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "kitchenTypeEntity")
    private List<CategoryDishEntity> categories;

    @OneToMany(mappedBy = "kitchenTypeEntity")
    private List<RestaurantEntity> restaurantEntities;
}