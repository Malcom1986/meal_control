package ru.maksimlitvinov.nutrition_control.model;


import jakarta.persistence.Entity;
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
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private int caloriesPerServing;
    private double protein;
    private double fat;
    private double carbohydrates;

}