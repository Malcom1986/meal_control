package ru.maksimlitvinov.nutrition_control.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Email
    String email;

    @Min(18)
    @Max(120)
    int age;

    @Min(20)
    @Max(300)
    double weight;

    @Min(50)
    @Max(300)
    double height;

    User.Gender gender;

    User.Goal goal;
}