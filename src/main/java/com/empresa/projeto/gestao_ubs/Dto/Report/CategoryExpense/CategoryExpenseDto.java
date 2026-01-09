package com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense;

public record CategoryExpenseDto(
    Long category_id,
    String categoryName,
    Double maxAmount
){}
