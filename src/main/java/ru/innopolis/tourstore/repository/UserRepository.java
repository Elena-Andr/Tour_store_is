package ru.innopolis.tourstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.tourstore.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByName(String name);
}
