package ru.maksimlitvinov.nutrition_control.dto.dish;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link ru.maksimlitvinov.nutrition_control.model.Dish}
 */
@Getter
@Setter
public class DishCreateDTO {
    private String name;
    @Min(0)
    private int caloriesPerServing;
    @Min(0)
    private double protein;
    @Min(0)
    private double fat;
    @Min(0)
    private double carbohydrates;
}