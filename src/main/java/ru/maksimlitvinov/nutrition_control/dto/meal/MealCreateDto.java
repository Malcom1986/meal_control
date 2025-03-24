package ru.maksimlitvinov.nutrition_control.dto.meal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for {@link ru.maksimlitvinov.nutrition_control.model.Dish}
 */
@Getter
@Setter
public class MealCreateDto {
    private String name;
    private Long user;
    private List<Long> dishes;
}
