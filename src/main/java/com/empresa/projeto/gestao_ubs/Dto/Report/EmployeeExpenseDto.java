package com.empresa.projeto.gestao_ubs.Dto.Report;

import java.time.LocalDate;

public record EmployeeExpenseDto(
        Long expenseId,
        LocalDate date,
        String description,
        String categoryName,
        Double amount,
        String currencyCode,
        String status
) {}