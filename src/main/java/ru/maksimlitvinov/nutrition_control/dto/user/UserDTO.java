package ru.maksimlitvinov.nutrition_control.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.maksimlitvinov.nutrition_control.model.User;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private User.Goal goal;
    private User.Gender gender;
}
