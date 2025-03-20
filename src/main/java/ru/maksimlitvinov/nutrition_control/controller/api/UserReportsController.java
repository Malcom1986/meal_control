package ru.maksimlitvinov.nutrition_control.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maksimlitvinov.nutrition_control.dto.meal.MealDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyReportDto;
import ru.maksimlitvinov.nutrition_control.dto.reports.DailyTargetReportDto;
import ru.maksimlitvinov.nutrition_control.service.ReportService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/{id}/reports")
@AllArgsConstructor
public class UserReportsController {

    private final ReportService reportService;

    @GetMapping("daily")
    public DailyReportDto getDailyReport(@PathVariable long id) {
        var today = LocalDate.now();
        return reportService.getDailyReport(id, today);
    }

    @GetMapping("target")
    public DailyTargetReportDto getDailyTargetReport(@PathVariable long id) {
        var today = LocalDate.now();
        return reportService.getDailyTargetReport(id, today);
    }

    @GetMapping("summary")
    public List<MealDto> getSummaryReport(@PathVariable long id) {
        return reportService.getSummaryReport(id);
    }


}

