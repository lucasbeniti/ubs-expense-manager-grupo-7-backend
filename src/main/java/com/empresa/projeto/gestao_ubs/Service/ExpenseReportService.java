package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpenseReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseReportService {
    List<EmployeeExpenseReportDto> getEmployeeExpenseReport(
            LocalDate start,
            LocalDate end,
            Long employeeId,
            Long categoryId
    );
}
