package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
