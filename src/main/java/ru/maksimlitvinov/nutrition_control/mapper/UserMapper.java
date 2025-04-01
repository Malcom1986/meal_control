package ru.maksimlitvinov.nutrition_control.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.maksimlitvinov.nutrition_control.dto.user.UserCreateDTO;
import ru.maksimlitvinov.nutrition_control.dto.user.UserDTO;
import ru.maksimlitvinov.nutrition_control.model.Role;
import ru.maksimlitvinov.nutrition_control.model.User;
import ru.maksimlitvinov.nutrition_control.repository.RoleRepository;

import java.util.HashSet;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Mapping(source = "password", target = "passwordDigest")
    public abstract User toEntity(UserCreateDTO userCreateDTO);

    public abstract UserDTO toUserDTO(User user);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO data) {
        var password = data.getPassword();
        var encryptedPassword = passwordEncoder.encode(password);
        data.setPassword(encryptedPassword);
    }

}
