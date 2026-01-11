package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.time.LocalDate;

public record EmployeeExpenseDto(
        Long expense_id,
        LocalDate date,
        String description,
        String category_name,
        Double amount,
        String currency_code,
        String status
) {}