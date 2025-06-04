package ru.cleancode.pizzaspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category_dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "kitchen_type_id")
    private KitchenTypeEntity kitchenTypeEntity;

    @OneToMany(mappedBy = "category")
    private List<DishEntity> dishes;
}
