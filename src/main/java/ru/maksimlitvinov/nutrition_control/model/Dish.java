package ru.maksimlitvinov.nutrition_control.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    @Min(0)
    private int caloriesPerServing;
    @Min(0)
    private double protein;
    @Min(0)
    private double fat;
    @Min(0)
    private double carbohydrates;

}

