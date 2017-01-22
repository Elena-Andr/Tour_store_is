package ru.innopolis.tourstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.tourstore.entity.TourEntity;

public interface TourRepository extends JpaRepository<TourEntity, Integer> {
}
