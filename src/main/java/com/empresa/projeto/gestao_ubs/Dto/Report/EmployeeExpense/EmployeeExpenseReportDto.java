package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.math.BigDecimal;
import java.util.List;

public record EmployeeExpenseReportDto(
        Long employee_id,
        String employee_name,
        BigDecimal total_amount,
        List<EmployeeExpenseDto> expenses
) {}