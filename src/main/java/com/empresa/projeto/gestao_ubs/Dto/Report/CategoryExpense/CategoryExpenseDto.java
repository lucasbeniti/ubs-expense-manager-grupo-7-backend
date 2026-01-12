package com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense;

import java.math.BigDecimal;

public record CategoryExpenseDto(
    Long categoryId,
    String categoryName,
    BigDecimal total
){}
