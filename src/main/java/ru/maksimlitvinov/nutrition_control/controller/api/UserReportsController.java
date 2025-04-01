package ru.maksimlitvinov.nutrition_control.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyReportDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyTargetReportDto;
import ru.maksimlitvinov.nutrition_control.repository.UserRepository;
import ru.maksimlitvinov.nutrition_control.service.ReportService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/reports")
@AllArgsConstructor
public class UserReportsController {

    private final ReportService reportService;

    private final UserRepository userRepository;

    @GetMapping("/daily")
    public DailyReportDto getDailyReport(Authentication principal) {
        var today = LocalDate.now();
        var currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        return reportService.getDailyReport(currentUser.getId(), today);
    }

    @GetMapping("/target")
    public DailyTargetReportDto getDailyTargetReport(Principal principal) {
        var today = LocalDate.now();
        var currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        return reportService.getDailyTargetReport(currentUser.getId(), today);
    }

    @GetMapping("/summary")
    public List<MealDto> getSummaryReport(Principal principal) {
        var currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        return reportService.getSummaryReport(currentUser.getId());
    }
}

