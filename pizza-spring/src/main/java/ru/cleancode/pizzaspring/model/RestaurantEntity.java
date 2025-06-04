package ru.cleancode.pizzaspring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "kitchen_type_id")
    private KitchenTypeEntity kitchenTypeEntity;

    @Column(name = "is_working")
    private Boolean isWorking = true;

    @OneToMany(mappedBy = "restaurantEntity")
    private List<IngredientEntity> ingredientEntities;

    @OneToMany(mappedBy = "restaurantEntity")
    private List<CustomerOrderEntity> orders;
}
