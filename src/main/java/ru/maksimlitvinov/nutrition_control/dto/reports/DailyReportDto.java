package ru.maksimlitvinov.nutrition_control.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.maksimlitvinov.nutrition_control.dto.dish.DishDto;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DailyReportDto {
    private LocalDate date;
    private Integer totalCalories;
    private List<MealDto> meals;
}
