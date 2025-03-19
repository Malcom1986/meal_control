package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}