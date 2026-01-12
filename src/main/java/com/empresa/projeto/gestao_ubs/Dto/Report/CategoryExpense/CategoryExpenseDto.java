package com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense;

import java.math.BigDecimal;

public record CategoryExpenseDto(
    Long category_id,
    String category_name,
    BigDecimal total
){}
