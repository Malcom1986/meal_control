package ru.maksimlitvinov.nutrition_control.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maksimlitvinov.nutrition_control.dto.LoginRequest;
import ru.maksimlitvinov.nutrition_control.util.JWTUtil;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String auth(@RequestBody LoginRequest loginRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);
        return jwtUtil.generateToken(loginRequest.getEmail());
    }
}
