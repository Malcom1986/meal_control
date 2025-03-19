package ru.maksimlitvinov.nutrition_control.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import ru.maksimlitvinov.nutrition_control.model.User;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
public class UserCreateDTO {
    String name;
    String email;
    int age;
    double weight;
    double height;
    User.Gender gender;
    User.Goal goal;
}