package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
    boolean existsByRoleName(String name);
}
