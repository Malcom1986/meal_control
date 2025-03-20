package ru.maksimlitvinov.nutrition_control.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealCreateDto;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;
import ru.maksimlitvinov.nutrition_control.exceptions.EntityNotFoundException;
import ru.maksimlitvinov.nutrition_control.mapper.MealMapper;
import ru.maksimlitvinov.nutrition_control.model.Meal;
import ru.maksimlitvinov.nutrition_control.repository.MealRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealsController {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    @GetMapping
    public List<MealDto> getAll() {

        return mealRepository.findAll().stream()
                .map(mealMapper::toMealDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MealDto getOne(@PathVariable Long id) {
        Optional<Meal> mealOptional = mealRepository.findById(id);
        var meal =  mealOptional
                .orElseThrow(() -> new EntityNotFoundException( "Entity with id `%s` not found".formatted(id)));
        return mealMapper.toMealDto(meal);
    }

    @PostMapping
    public MealDto create(@RequestBody MealCreateDto mealDto) {
        var meal = mealMapper.toEntity(mealDto);
        mealRepository.save(meal);
        return mealMapper.toMealDto(meal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }
}
