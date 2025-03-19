package ru.maksimlitvinov.nutrition_control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.maksimlitvinov.nutrition_control.dto.user.UserCreateDTO;
import ru.maksimlitvinov.nutrition_control.dto.user.UserDTO;
import ru.maksimlitvinov.nutrition_control.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCreateDTO userCreateDTO);

    UserDTO toUserDTO(User user);
}