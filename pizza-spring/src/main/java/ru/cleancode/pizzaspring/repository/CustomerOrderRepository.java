package ru.cleancode.pizzaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cleancode.pizzaspring.model.CustomerOrderEntity;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {

    @Query("SELECT o FROM CustomerOrderEntity o WHERE o.customerEntity.id = :customerId")
    List<CustomerOrderEntity> findByCustomerId(Long customerId);
}
