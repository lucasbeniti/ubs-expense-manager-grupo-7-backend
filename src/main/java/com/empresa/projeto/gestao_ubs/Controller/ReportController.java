package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense.EmployeeExpenseReportDto;
import com.empresa.projeto.gestao_ubs.Service.CategoryReportService;
import com.empresa.projeto.gestao_ubs.Service.DepartmentReportService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Reports", description = "APIs endpoints for managing reports")
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
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long employee_id,
            @RequestParam(required = false) Long category_id
    ) {
        return ResponseEntity.ok(
                expenseReportService.getEmployeeExpenseReport(startDate, endDate, employee_id, category_id)
        );
    }

    private final CategoryReportService CategoryReportService;

    @GetMapping("/category-expenses")
    @Operation(
            summary = "Category expenses report",
            description = "Returns an expense report grouped by category"
    )
    public ResponseEntity<List<CategoryExpenseDto>> getCategoryExpenseReport(
            @RequestParam(required = false) Long category_id
    ) {
        return ResponseEntity.ok(CategoryReportService.ExpenseByCategory(
                        category_id
                )
        );
    }

    private final DepartmentReportService DepartmentReportService;

    @GetMapping("/department-budget-comparative")
    @Operation(
            summary = "Department budget comparative",
            description = "Returns a comparison between the total spent and the monthly limit"
    )
    public ResponseEntity<List<DepartmentExpenseDto>> getDepartmentBudgetComparative(
            @RequestParam(required = false) Long department_id
    ) {
        return ResponseEntity.ok(
                DepartmentReportService.getDepartmentBudgetComparative(
                        department_id
                )
        );
    }
}