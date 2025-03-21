package ru.maksimlitvinov.nutrition_control.controller.api;

import org.instancio.Instancio;
import org.instancio.Select;
import ru.maksimlitvinov.nutrition_control.model.Dish;
import ru.maksimlitvinov.nutrition_control.model.Meal;
import ru.maksimlitvinov.nutrition_control.model.User;
import net.datafaker.Faker;

import static org.instancio.Select.field;

public class TestUtils {
    private static final Faker faker = new Faker();

    public static User generateUser() {
        return Instancio.of(User.class)
                .ignore(field(User.class, "id"))
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(Select.field(User::getHeight), () -> faker.number().numberBetween(100, 180))
                .supply(Select.field(User::getWeight), () -> faker.number().numberBetween(50, 150))
                .supply(Select.field(User::getAge), () -> faker.number().numberBetween(18, 60))
                .create();
    }

    public static Dish generateDish() {
        return Instancio.of(Dish.class)
                .ignore(field(Dish.class, "id"))
                .supply(Select.field(Dish::getFat), () -> faker.number().numberBetween(0, 30))
                .supply(Select.field(Dish::getProtein), () -> faker.number().numberBetween(0, 30))
                .supply(Select.field(Dish::getCarbohydrates), () -> faker.number().numberBetween(0, 30))
                .create();
    }
    public static Meal generateMeal() {
        return Instancio.of(Meal.class)
                .ignore(Select.field(Meal::getId))
                .ignore(Select.field(Meal::getUser))
                .ignore(Select.field(Meal::getDishes))
                .create();
    }


}
