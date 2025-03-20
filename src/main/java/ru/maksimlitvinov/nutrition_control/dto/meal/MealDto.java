package ru.maksimlitvinov.nutrition_control.dto.meal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MealDto {
    private Long id;
    private String name;
    private Long user;
    private List<String> dishes;
    private LocalDate createdAt;

}
