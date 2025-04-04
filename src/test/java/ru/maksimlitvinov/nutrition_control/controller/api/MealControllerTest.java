package ru.maksimlitvinov.nutrition_control.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealCreateDto;
import ru.maksimlitvinov.nutrition_control.model.Dish;
import ru.maksimlitvinov.nutrition_control.model.Meal;
import ru.maksimlitvinov.nutrition_control.model.User;
import ru.maksimlitvinov.nutrition_control.repository.DishRepository;
import ru.maksimlitvinov.nutrition_control.repository.MealRepository;
import ru.maksimlitvinov.nutrition_control.repository.RoleRepository;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class MealControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DishRepository dishRepository;

    private Meal testMeal;
    private User testUser;
    private Dish testDish;

    private SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor token;

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
        mealRepository.deleteAll();
        userRepository.deleteAll();
        dishRepository.deleteAll();

        testUser = TestUtils.generateUser();
        var role = roleRepository.findByRoleName("user").orElseThrow();
        testUser.setRoles(Set.of(role));
        userRepository.save(testUser);

        testDish = TestUtils.generateDish();
        dishRepository.save(testDish);

        testMeal = TestUtils.generateMeal();
        testMeal.setUser(testUser);
        testMeal.setDishes(List.of(testDish));
        mealRepository.save(testMeal);
        token = jwt().jwt(builder -> builder.subject(testUser.getEmail()));

    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/meals").with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/api/meals/{id}", testMeal.getId()).with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        var data = new MealCreateDto();
        data.setDishes(List.of(testDish.getId()));
        data.setName("testMeal");

        var request = post("/api/meals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request.with(token))
                .andExpect(status().isCreated());

    }

    @Test
    void testDelete() throws Exception {

        var request = delete("/api/meals/{id}", testMeal.getId());

        mockMvc.perform(request.with(token))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDailyMealReport() throws Exception {
        mockMvc.perform(get("/api/users/reports/daily", testUser.getId()).with(token))
                .andExpect(status().isOk());
    }

    @Test
    public void testTargetMealReport() throws Exception {
        mockMvc.perform(get("/api/users/reports/target", testUser.getId()).with(token))
                .andExpect(status().isOk());
    }

    @Test
    public void testSummaryMealReport() throws Exception {
        mockMvc.perform(get("/api/users/reports/summary", testUser.getId()).with(token))
                .andExpect(status().isOk());
    }
}
