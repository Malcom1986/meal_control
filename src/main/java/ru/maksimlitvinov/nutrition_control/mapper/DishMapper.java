package ru.maksimlitvinov.nutrition_control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.maksimlitvinov.nutrition_control.dto.dish.DishCreateDTO;
import ru.maksimlitvinov.nutrition_control.dto.dish.DishDto;
import ru.maksimlitvinov.nutrition_control.model.Dish;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DishMapper {
    Dish toEntity(DishCreateDTO dishDto);

    DishDto toDishDto(Dish dish);
}
