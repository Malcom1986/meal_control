package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
