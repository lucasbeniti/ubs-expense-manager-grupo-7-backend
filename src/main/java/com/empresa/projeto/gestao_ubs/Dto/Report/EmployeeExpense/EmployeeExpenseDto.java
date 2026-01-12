package com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeExpenseDto(
        Long expense_id,
        LocalDate date,
        String description,
        String category_name,
        BigDecimal amount,
        String currency_code,
        String status
) {}