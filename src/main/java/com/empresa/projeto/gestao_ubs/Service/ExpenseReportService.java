package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense.EmployeeExpenseReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseReportService {
    List<EmployeeExpenseReportDto> getEmployeeExpenseReport(
            LocalDate start,
            LocalDate end,
            Long employee_id,
            Long category_id
    );
}
