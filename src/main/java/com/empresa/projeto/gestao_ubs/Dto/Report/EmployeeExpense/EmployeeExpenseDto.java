package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.time.LocalDate;

public record EmployeeExpenseDto(
        Long expense_id,
        LocalDate date,
        String description,
        String categoryName,
        Double amount,
        String currencyCode,
        String status
) {}