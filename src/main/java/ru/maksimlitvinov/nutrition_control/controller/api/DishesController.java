package ru.maksimlitvinov.nutrition_control.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maksimlitvinov.nutrition_control.dto.dish.DishCreateDTO;
import ru.maksimlitvinov.nutrition_control.dto.dish.DishDto;
import ru.maksimlitvinov.nutrition_control.exceptions.EntityNotFoundException;
import ru.maksimlitvinov.nutrition_control.mapper.DishMapper;
import ru.maksimlitvinov.nutrition_control.model.Dish;
import ru.maksimlitvinov.nutrition_control.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishesController {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @GetMapping
    public List<DishDto> getAll() {
        return dishRepository.findAll().stream().map(dishMapper::toDishDto).toList();
    }

    @GetMapping("/{id}")
    public DishDto getOne(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        var dish =  dishOptional.orElseThrow(() ->
                new EntityNotFoundException("Entity with id `%s` not found".formatted(id)));
        return dishMapper.toDishDto(dish);
    }

    @PostMapping
    public DishDto create(@RequestBody DishCreateDTO dishDto) {
        var dish = dishMapper.toEntity(dishDto);
        dishRepository.save(dish);
        return dishMapper.toDishDto(dish);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dishRepository.deleteById(id);
    }
}
