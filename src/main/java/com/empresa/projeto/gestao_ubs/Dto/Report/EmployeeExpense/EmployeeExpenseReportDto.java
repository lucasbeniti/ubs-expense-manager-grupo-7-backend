package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.math.BigDecimal;
import java.util.List;

public record EmployeeExpenseReportDto(
        Long employeeId,
        String employeeName,
        BigDecimal totalAmount,
        List<EmployeeExpenseDto> expenses
) {}