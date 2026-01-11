package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.util.List;

public record EmployeeExpenseReportDto(
        Long employee_id,
        String employee_name,
        Double total_amount,
        List<EmployeeExpenseDto> expenses
) {}