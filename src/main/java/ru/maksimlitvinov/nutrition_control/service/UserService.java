package ru.maksimlitvinov.nutrition_control.service;

import org.springframework.stereotype.Service;
import ru.maksimlitvinov.nutrition_control.model.User;

@Service
public class UserService {
    public double calculateDailyCaloricNorm(User user) {
        double basalMetabolicRate = 66 + (6.2 * user.getWeight()) + (12.7 * user.getHeight()) - (6.8 * user.getAge());
        return basalMetabolicRate * getActivityMultiplier(user.getGoal());
    }

    private double getActivityMultiplier(User.Goal goal) {
        switch (goal) {
            case LOSE:
                return 1.2;
            case MAINTAIN:
                return 1.5;
            case GAIN:
                return 1.8;
            default:
                throw new UnsupportedOperationException("Unsupported goal");
        }
    }
}
