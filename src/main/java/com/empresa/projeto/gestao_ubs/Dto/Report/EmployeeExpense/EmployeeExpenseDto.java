package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeExpenseDto(
        Long expenseId,
        LocalDate date,
        String description,
        String categoryName,
        BigDecimal amount,
        String currencyCode,
        String status
) {}