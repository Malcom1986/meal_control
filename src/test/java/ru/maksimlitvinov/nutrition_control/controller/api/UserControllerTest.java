package ru.maksimlitvinov.nutrition_control.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.maksimlitvinov.nutrition_control.dto.user.UserCreateDTO;
import ru.maksimlitvinov.nutrition_control.model.User;
import ru.maksimlitvinov.nutrition_control.repository.RoleRepository;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class UserControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    private User testUser;

    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @DynamicPropertySource
    private static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }


    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        testUser = TestUtils.generateUser();
        var role = roleRepository.findByRoleName("user").orElseThrow();
        testUser.setRoles(Set.of(role));
        userRepository.save(testUser);
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/users").with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/api/users/{id}", testUser.getId()).with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        var data = new UserCreateDTO();
        data.setName("test");
        data.setPassword("qwerty");
        data.setEmail("test@mail.com");
        data.setAge(18);
        data.setHeight(175);
        data.setWeight(80);
        data.setGender(User.Gender.MALE);
        data.setGoal(User.Goal.LOSE);

        var request = post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request.with(jwt()))
                .andExpect(status().isCreated());

        var actualUser = userRepository.findByEmail(data.getEmail()).orElse(null);
        assertNotNull(actualUser);
        assertEquals(data.getName(), actualUser.getName());
    }
    @Test
    void testDelete() throws Exception {

        var request = delete("/api/users/{id}", testUser.getId());

        mockMvc.perform(request.with(jwt()))
                .andExpect(status().isNoContent());

        var actualUser = userRepository.findByEmail(testUser.getEmail()).orElse(null);
        assertNull(actualUser);
    }
}
