package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}