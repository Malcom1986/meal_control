package ru.maksimlitvinov.nutrition_control.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String email;
    private int age;
    private double weight;
    private double height;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    public enum Gender { MALE, FEMALE }
    public enum Goal { LOSE, MAINTAIN, GAIN }
}
