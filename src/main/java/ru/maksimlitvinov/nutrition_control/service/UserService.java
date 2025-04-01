package ru.maksimlitvinov.nutrition_control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.maksimlitvinov.nutrition_control.dto.user.UserCreateDTO;
import ru.maksimlitvinov.nutrition_control.mapper.UserMapper;
import ru.maksimlitvinov.nutrition_control.model.Role;
import ru.maksimlitvinov.nutrition_control.model.User;
import ru.maksimlitvinov.nutrition_control.repository.RoleRepository;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsManager {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void createUser(UserDetails user) {

    }

    public User createAndReturnUser(UserCreateDTO data) {
        var user = userMapper.toEntity(data);
        assignRole(user, "user");
        return userRepository.save(user);
    }

    public void assignRole(User user, String roleName) {
        var role = roleRepository.findByRoleName(roleName).orElseThrow();

        if (user.getRoles() == null) {
            var roles = new HashSet<Role>();
            roles.add(role);
            user.setRoles(roles);
        }
    }


    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
