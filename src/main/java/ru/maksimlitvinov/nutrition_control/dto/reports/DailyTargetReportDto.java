package ru.maksimlitvinov.nutrition_control.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DailyTargetReportDto {
    private boolean calorieGoalAchieved;
    private double targetCalories;
    private int actualCalories;
}
