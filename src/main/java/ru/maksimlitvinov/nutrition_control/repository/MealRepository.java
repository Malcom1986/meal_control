package ru.maksimlitvinov.nutrition_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.maksimlitvinov.nutrition_control.model.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MealRepository extends JpaRepository<Meal, Long> {
    public List<Meal> findByUserIdAndCreatedAt(Long userId, LocalDate date);
    List<Meal> findByUserIdOrderByCreatedAt(Long userId);
}