package ru.maksimlitvinov.nutrition_control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealCreateDto;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;
import ru.maksimlitvinov.nutrition_control.exceptions.EntityNotFoundException;
import ru.maksimlitvinov.nutrition_control.model.Dish;
import ru.maksimlitvinov.nutrition_control.model.Meal;
import ru.maksimlitvinov.nutrition_control.repository.DishRepository;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class MealMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    public abstract Meal toEntity(MealCreateDto mealDto);

    @Mapping(source = "user.id", target = "user")
    public abstract MealDto toMealDto(Meal meal);

    public List<Dish> toDishes(List<Long> dishIds) {
        return dishIds.stream()
                .map((dishId) -> dishRepository.findById(dishId)
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .toList();
    }

    public List<String> toDishNames(List<Dish> dishes) {
        return dishes.stream().map(Dish::getName).toList();
    }
}
