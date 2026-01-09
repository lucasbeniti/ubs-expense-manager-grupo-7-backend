package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpenseReportDto;
import com.empresa.projeto.gestao_ubs.Service.ExpenseReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Reports", description = "APIs endpoints for reports")
@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {

    private final ExpenseReportService expenseReportService;

    @GetMapping("/employee-expenses")
    @Operation(
            summary = "Employee expense report",
            description = "Returns an expense report grouped by employee"
    )
    public ResponseEntity<List<EmployeeExpenseReportDto>> getEmployeeExpenseReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(
                expenseReportService.getEmployeeExpenseReport(startDate, endDate, employeeId, categoryId)
        );
    }
}