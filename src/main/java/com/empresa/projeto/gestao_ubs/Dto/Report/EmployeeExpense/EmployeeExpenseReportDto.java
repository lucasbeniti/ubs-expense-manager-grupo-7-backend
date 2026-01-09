package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.util.List;

public record EmployeeExpenseReportDto(
        Long employee_id,
        String employeeName,
        Double totalAmount,
        List<EmployeeExpenseDto> expenses
) {}