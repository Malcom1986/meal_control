package ru.maksimlitvinov.nutrition_control.dto.dish;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link ru.maksimlitvinov.nutrition_control.model.Dish}
 */
@Getter
@Setter
public class DishDto {
    private Long id;
    private String name;
    private int caloriesPerServing;
    private double protein;
    private double fat;
    private double carbohydrates;
}
