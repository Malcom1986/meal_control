package ru.maksimlitvinov.nutrition_control.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import ru.maksimlitvinov.nutrition_control.model.User;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
public class UserCreateDTO {
    private String name;

    @Email
    private String email;

    @Min(18)
    @Max(120)
    private int age;

    @Min(20)
    @Max(300)
    private double weight;

    @Min(50)
    @Max(300)
    private double height;

    private User.Gender gender;

    private User.Goal goal;
}
