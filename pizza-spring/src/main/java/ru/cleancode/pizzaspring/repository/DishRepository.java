package ru.cleancode.pizzaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cleancode.pizzaspring.model.DishEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT d FROM DishEntity d " +
            "JOIN d.category c " +
            "JOIN c.kitchenTypeEntity k " +
            "JOIN k.restaurantEntities r " +
            "WHERE r.id = :restaurantId")
    List<DishEntity> findByRestaurantId(Long restaurantId);

    @Query("SELECT d FROM DishEntity d " +
            "JOIN d.category c " +
            "JOIN c.kitchenTypeEntity k " +
            "WHERE k.id = :kitchenTypeId")
    List<DishEntity> findByKitchenTypeId(Long kitchenTypeId);

    Optional<DishEntity> findByName(String name);
}