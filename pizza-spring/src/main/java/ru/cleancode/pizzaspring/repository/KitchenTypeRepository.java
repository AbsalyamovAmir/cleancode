package ru.cleancode.pizzaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cleancode.pizzaspring.model.KitchenTypeEntity;

@Repository
public interface KitchenTypeRepository extends JpaRepository<KitchenTypeEntity, Long> {
}