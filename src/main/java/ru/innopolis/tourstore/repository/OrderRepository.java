package ru.innopolis.tourstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.tourstore.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
