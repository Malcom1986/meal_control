package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimlitvinov.nutrition_control.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserIdAndCreatedAt(Long userId, LocalDate date);
    List<Meal> findByUserIdOrderByCreatedAt(Long userId);
}
