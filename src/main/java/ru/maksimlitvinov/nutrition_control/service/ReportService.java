package ru.maksimlitvinov.nutrition_control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyReportDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyTargetReportDto;
import ru.maksimlitvinov.nutrition_control.exceptions.EntityNotFoundException;
import ru.maksimlitvinov.nutrition_control.mapper.MealMapper;
import ru.maksimlitvinov.nutrition_control.model.Dish;
import ru.maksimlitvinov.nutrition_control.model.Meal;
import ru.maksimlitvinov.nutrition_control.repository.MealRepository;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final UserService userService;
    private final MealMapper mealMapper;

    public DailyReportDto getDailyReport(long userId, LocalDate forDate) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Entity with id `%s` not found".formatted(userId));
        }
        var allMealsForToday = mealRepository.findByUserIdAndCreatedAt(userId, forDate);
        var totalCalories = getTotalCalories(allMealsForToday);
        var mealDtos = allMealsForToday.stream().map(mealMapper::toMealDto).toList();
        return new DailyReportDto(forDate, totalCalories, mealDtos);
    }

    public DailyTargetReportDto getDailyTargetReport(long userId, LocalDate forDate) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id `%s` not found".formatted(userId)));
        var dailyTargetCalories = userService.calculateDailyCaloricNorm(user);
        var allMealsForToday = mealRepository.findByUserIdAndCreatedAt(userId, forDate);
        var actualCalories = getTotalCalories(allMealsForToday);
        var calorieGoalAchieved = dailyTargetCalories <= actualCalories;
        return new DailyTargetReportDto(calorieGoalAchieved, dailyTargetCalories, actualCalories);
    }

    public List<MealDto> getSummaryReport(long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id `%s` not found".formatted(userId)));
        var allUserMeals = mealRepository.findByUserIdOrderByCreatedAt(userId);
        return allUserMeals.stream().map(mealMapper::toMealDto).toList();
    }

    private int getTotalCalories(List<Meal> meals) {
        return meals.stream()
                .flatMap((meal) -> meal.getDishes().stream())
                .mapToInt(Dish::getCaloriesPerServing)
                .sum();
    }
}
