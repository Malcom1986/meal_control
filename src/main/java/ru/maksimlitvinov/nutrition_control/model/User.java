package ru.maksimlitvinov.nutrition_control.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    @Email
    @Column(unique = true)
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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    public enum Gender { MALE, FEMALE }
    public enum Goal { LOSE, MAINTAIN, GAIN }
}
