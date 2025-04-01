package ru.maksimlitvinov.nutrition_control.component;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.maksimlitvinov.nutrition_control.model.Role;
import ru.maksimlitvinov.nutrition_control.repository.RoleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addRoles();
    }

    private void addRoles() {
        var roles = List.of(
                new Role("user")
        );
        roles.stream()
                .filter((role -> !roleRepository.existsByRoleName(role.getRoleName())))
                .forEach(roleRepository::save);
    }
}
